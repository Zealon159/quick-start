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
        sb.append("{\"menuid\":\"1\",\"icon\":\"\",\"menuname\":\"索引/文档\",\"menus\":[");
        sb.append("{\"menuid\":\"3\",\"menuname\":\"创建索引\",\"icon\":\"icon-add\",\"url\":\"/indexes/create-page\"}," );
        sb.append("{\"menuid\":\"4\",\"menuname\":\"索引列表\",\"icon\":\"icon-list\",\"url\":\"/indexes/list\"},");
        sb.append("{\"menuid\":\"5\",\"menuname\":\"创建文档\",\"icon\":\"icon-list\",\"url\":\"/doc/create-page\"}");
        sb.append("]}");

        // 查询
        sb.append(",{\"menuid\":\"6\",\"icon\":\"\",\"menuname\":\"查询\",");
        sb.append("\"menus\":[{\"menuid\":\"7\",\"menuname\":\"基础查询\",\"icon\":\"icon-grid\",\"url\":\"/basic/search-page\"},");
        sb.append("{\"menuid\":\"8\",\"menuname\":\"高级查询\",\"icon\":\"icon-search\",\"url\":\"/advanced/search-page\"}" );

        sb.append("]}");

        sb.append("]}");
        return sb.toString();
    }
}
