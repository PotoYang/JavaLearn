// layui.config({
//     base: 'js/modules/'
// }).extend('editor').use('editor', function (myeditor) {
//     var editor = myeditor.getEditor();
//     $('#editor').load('markdown.md',
//         function (data) {
//             myeditor.render();
//         }
//     );
// });
layui.config({
    base: 'js/modules/'
}).extend(['utils', 'editor']).use(['selectPage', 'editor'], function (selectPagePlugin) {
    var $ = layui.$;
    var editor = layui.editor;
    //定义数组，在服务端返回的数据也以该格式返回：Array[{Object},{...}]
    var tag_data = [
        {id: 1, name: 'beijing', desc: '北京'},
        {id: 2, name: 'shenzhen', desc: '深圳'},
        {id: 3, name: 'shanghai', desc: '上海'},
        {id: 4, name: 'guangzhou', desc: '广州'}
    ];
    //showField：设置下拉列表中显示文本的列
    //keyField：设置下拉列表项目中项目的KEY值，用于提交表单
    //data：数据源，可以是JSON数据格式，也可以是URL
    var selectPageOption = {
        showField: 'desc',
        keyField: 'id',
        data: tag_data
    };
    var selectInput = document.getElementById("selectPage");
    //加载
    selectPagePlugin.selectPage(selectInput, selectPageOption);

    //获取文本
    $("#getText").click(function () {
        var txt = selectPagePlugin.selectPageText(selectInput);
        console.log("selectPageText:" + txt);
    });
    //清除
    $("#clearText").click(function () {
        selectPagePlugin.selectPageClear(selectInput);
    });
    //刷新
    $("#refreshText").click(function () {
        selectPagePlugin.selectPageRefresh(selectInput);
    });
    //启用
    $("#selectEnabled").click(function () {
        selectPagePlugin.selectPageDisabled(selectInput, false);
    });
    //弃用
    $("#selectDisabled").click(function () {
        selectPagePlugin.selectPageDisabled(selectInput, true);
    });

    $('#editor').load('markdown.md',
        function (data) {
            editor.render();
        }
    );
});