package cn.zealon.easyui.service;

import org.springframework.stereotype.Service;

/**
 * @auther: Zealon
 * @Date: 2018-06-27 16:14
 */
@Service
public class MenuServcie {

    //菜单json
    public String getMenu(){
        StringBuffer sb = new StringBuffer();

        sb.append("{\"menus\":[");

        //form
        sb.append("{\"menuid\":\"1\",\"icon\":\"\",\"menuname\":\"组件\",");
        sb.append("\"menus\":[{\"menuid\":\"2\",\"menuname\":\"combobox\",\"icon\":\"icon-grid\",\"url\":\"/combox\"},");
        sb.append("{\"menuid\":\"3\",\"menuname\":\"datebox\",\"icon\":\"icon-pkg\",\"url\":\"/datebox\"}," );
        sb.append("{\"menuid\":\"4\",\"menuname\":\"tab\",\"icon\":\"icon-copy\",\"url\":\"/tab\"},");
        sb.append("{\"menuid\":\"5\",\"menuname\":\"tab-multi\",\"icon\":\"icon-copy\",\"url\":\"/tab-multi\"}");
        sb.append("]}");

        //datagrid
        sb.append(",{\"menuid\":\"6\",\"icon\":\"\",\"menuname\":\"数据表格\",");
        sb.append("\"menus\":[{\"menuid\":\"7\",\"menuname\":\"DataGrid\",\"icon\":\"icon-grid\",\"url\":\"/datagrid\"},");
        sb.append("{\"menuid\":\"8\",\"menuname\":\"EditorGrid\",\"icon\":\"icon-pkg\",\"url\":\"/editorgrid\"}" );
        //sb.append(",{\"menuid\":\"9\",\"menuname\":\"档案室\",\"icon\":\"icon-copy\",\"url\":\"r?wf_num=P_DR_002\"}");
        sb.append("]}");

        sb.append("]}");
        return sb.toString();
    }

    public String getLeftMenu(){
        String str = "[{\"id\":\"root\",\"text\":\"所有分类\",\"WF_OrUnid\":\"root\",\"children\":[{\"id\":\"001\",\"text\":\"2009年度（78）\",\"iconCls\":\"\",\"WF_OrUnid\":\"46DA0D30D11CB18D48257950002F6536\",\"state\":\"open\"},{\"id\":\"002\",\"text\":\"2010年度（98）\",\"iconCls\":\"\",\"WF_OrUnid\":\"EBB9E26F72BE9BC948257950002F6DCB\",\"state\":\"open\"},{\"id\":\"003\",\"text\":\"2011年度（108）\",\"iconCls\":\"\",\"WF_OrUnid\":\"F4E994848284749948257950002F741C\",\"state\":\"open\"},{\"id\":\"004\",\"text\":\"2012年度（101）\",\"iconCls\":\"\",\"WF_OrUnid\":\"FA940468A267E04C48257B95000CB2FC\",\"state\":\"open\"},{\"id\":\"005\",\"text\":\"2013年度（94）\",\"iconCls\":\"\",\"WF_OrUnid\":\"E58983BE21DF4FFF48257C44002A5CB3\",\"state\":\"open\"},{\"id\":\"006\",\"text\":\"2014年度（74）\",\"iconCls\":\"\",\"WF_OrUnid\":\"6A7A51A64734CAA348257C590026302E\",\"state\":\"open\"},{\"id\":\"007\",\"text\":\"2015年度（68）\",\"iconCls\":\"\",\"WF_OrUnid\":\"505D8D7862E4060948257DCD000C9690\",\"state\":\"open\"},{\"id\":\"008\",\"text\":\"2016年度（19）\",\"iconCls\":\"\",\"WF_OrUnid\":\"7392c1cc08eaa04f0108692022c5cac3a19e\",\"state\":\"open\"},{\"id\":\"009\",\"text\":\"2017年度<span style='color:gray'>（0）\",\"iconCls\":\"\",\"WF_OrUnid\":\"883733310a4d704d0708c7b07277ea14fc6c\",\"state\":\"open\"},{\"id\":\"010\",\"text\":\"2018年度<span style='color:gray'>（0）\",\"iconCls\":\"\",\"WF_OrUnid\":\"3d2bfc800c54504ee009df80056664fcc138\",\"state\":\"open\"}]},{\"id\":\"newDoc\",\"text\":\"新增文档\",\"WF_OrUnid\":\"newDoc\",\"iconCls\":\"icon-add\"},{\"id\":\"searchDoc\",\"text\":\"查询案卷\",\"WF_OrUnid\":\"searchDoc\",\"iconCls\":\"icon-search\"}]";
        return str;
    }
}
