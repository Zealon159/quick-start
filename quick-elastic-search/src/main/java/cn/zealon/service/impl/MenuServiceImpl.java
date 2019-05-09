package cn.zealon.service.impl;

import cn.zealon.service.MenuService;
import org.springframework.stereotype.Service;

/**
 * 菜单服务
 * @author: tangyl
 * @since: 2019/5/9
 * @version: 1.0
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Override
    public String getHomeMenu() {
        return this.getMenuData();
    }

    private String getMenuData(){
        StringBuffer sb = new StringBuffer();

        sb.append("{\"menus\":[");

        // 索引
        sb.append("{\"menuid\":\"1\",\"icon\":\"\",\"menuname\":\"索引\",\"menus\":[");
        sb.append("{\"menuid\":\"3\",\"menuname\":\"创建索引\",\"icon\":\"icon-add\",\"url\":\"/indexes/create-page\"}," );
        sb.append("{\"menuid\":\"4\",\"menuname\":\"索引列表\",\"icon\":\"icon-list\",\"url\":\"/indexes/list\"}");
        sb.append("]}");

        // 基础查询
        sb.append(",{\"menuid\":\"6\",\"icon\":\"\",\"menuname\":\"基础查询\",");
        sb.append("\"menus\":[{\"menuid\":\"7\",\"menuname\":\"DataGrid\",\"icon\":\"icon-grid\",\"url\":\"/datagrid\"},");
        sb.append("{\"menuid\":\"8\",\"menuname\":\"EditorGrid\",\"icon\":\"icon-pkg\",\"url\":\"/editorgrid\"}" );

        sb.append("]}");

        sb.append("]}");
        return sb.toString();
    }
}
