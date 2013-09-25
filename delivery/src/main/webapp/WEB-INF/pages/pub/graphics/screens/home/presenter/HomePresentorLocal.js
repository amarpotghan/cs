function HomePresenter(){

}

var rendererData;
var btnSelectionFlag = 0;

HomePresenter.handleViewChange = function(evt){
    switch(evt.currentTarget.id)
    {
        case "tileView":
            //console.log(":: Load Tile View Button Clicked ::");
            HomePresenter.loadViewItems(rendererData, EngineDataStore.getBaseURL()+"graphics/screens/home/htmls/renderers/TileViewRenderer.html");
            HomePresenter.btnFocus(".tileBtnCSS");
            break;

        case "listView":
            //console.log(":: Load List View Button Clicked ::");
            HomePresenter.loadViewItems(rendererData, EngineDataStore.getBaseURL()+"graphics/screens/home/htmls/renderers/ListViewRenderer.html");
            HomePresenter.btnFocus(".listBtnCSS");
            break;

        case "detailView":
            //console.log(":: Load Detail View Button Clicked ::");
            HomePresenter.loadViewItems(rendererData, EngineDataStore.getBaseURL()+"graphics/screens/home/htmls/renderers/DetailViewRenderer.html");
            HomePresenter.btnFocus(".detailBtnCSS");
            break;
    }
}

HomePresenter.loadViewItems = function(evt,currentTemplateView){
    MustacheWrapper.createUI(currentTemplateView,evt, function(currentViewStr){
        $('#viewHolder').html(currentViewStr);
    });
}

HomePresenter.slidePanel = function(evt){
    var btnId = evt.currentTarget.id;
    if (btnSelectionFlag==0){
        $("#typeHolder").html(evt.currentTarget.name);
        $("#panel").animate({right:'30px'},"slow",function(){
            HomePresenter.createTree(btnId);
        });
        btnSelectionFlag=1;
    }
    else if (btnSelectionFlag == 1 && ($("#typeHolder").html()== evt.currentTarget.name)){
        $("#panel").animate({right:'-200px'},"slow");
        HomePresenter.reset();
        btnSelectionFlag=0;
    }
    else {
        $("#typeHolder").html(evt.currentTarget.name);
        HomePresenter.createTree(btnId);
    }
    HomePresenter.changeSelectedBtn(evt.currentTarget.id);
}


HomePresenter.clearList = function(){
    var contextMenusHolder = document.getElementById('menus');
    contextMenusHolder.innerHTML = "";
}

HomePresenter.btnFocus = function(btn){
    $('.tileBtnCSS').css("border","0px");
    $('.listBtnCSS').css("border","0px");
    $('.detailBtnCSS').css("border","0px");
    $(btn).css("border","1px solid black");
}

HomePresenter.createTree = function(btnId){
    var urls;
    if(btnId == "btnPIM"){
        urls= EngineDataStore.getBaseURL()+"delivery/pim/list";
    }
    if(btnId == "btnMAM"){
        urls= EngineDataStore.getBaseURL()+"delivery/mam/list";
    }
    if(btnId == "btnMIM"){
        urls= EngineDataStore.getBaseURL()+"delivery/pim/list";
    }
    var treeObj = document.getElementById("assetsTree");
    var darkTree = ElementFactory.getLazyTree();
    darkTree.createTree(treeObj,urls);
}

HomePresenter.reset = function(){
    $("#btnMIM").css("background-image","url(/delivery/pages/graphics/screens/home/images/icons/MIM.png)");
    $("#btnPIM").css("background-image","url(/delivery/pages/graphics/screens/home/images/icons/PIM.png)");
    $("#btnMAM").css("background-image","url(/delivery/pages/graphics/screens/home/images/icons/MAM.png)");
}

HomePresenter.changeSelectedBtn = function(btnId){
    HomePresenter.reset();
    var urls;
    if(btnId == "btnPIM"){
        urls= EngineDataStore.getBaseURL()+"graphics/screens/home/images/icons/PIMb.png";
    }
    if(btnId == "btnMAM"){
        urls= EngineDataStore.getBaseURL()+"graphics/screens/home/images/icons/MAMb.png";
    }
    if(btnId == "btnMIM"){
        urls= EngineDataStore.getBaseURL()+"graphics/screens/home/images/icons/MIMb.png";
    }
    $('#'+btnId).css("background-image",'url("' + urls + '")');
}

$(document).bind("TREE_ITEM_CLICKED", function itemClickedHandler(e){
    if(e.nodeType == "Assortment"){
        HomePresenter.showAssortmentPanel(e.uiData);
    }else{
        HomePresenter.hideAssortPanel();
        rendererData = {"mydata":e.uiData};
        HomePresenter.loadViewItems(rendererData, EngineDataStore.getBaseURL()+"graphics/screens/home/htmls/renderers/TileViewRenderer.html");
        HomePresenter.btnFocus(".tileBtnCSS");
    }

});

HomePresenter.getChildrenForSelectedNode = function(node){
    var nodeDetails = [];
    for(var i=0; i< node.data.children.length; i++){
        var obj = new TreeObjectVO();
        obj.id = node.data.children[i].id;
        obj.title = node.data.children[i].title;
        obj.type = node.data.children[i].type;
        obj.path = node.data.children[i].path;
        obj.children = node.data.children[i].children;
        nodeDetails.push(obj);
    }
    return nodeDetails;
}

HomePresenter.getProductsForSelectedNode = function(node){
    var nodeDetails = [];
    for(var i=0; i< node.data.products.length; i++){
        var obj = new ProductVO();
        obj.id = node.data.products[i].id;
        obj.title = node.data.products[i].title;
        obj.type = node.data.products[i].type;
        nodeDetails.push(obj);
    }
    return nodeDetails;
}

HomePresenter.showAssortmentPanel = function(rendererData){
    HomePresenter.unHideAssortPanel();
    alert(JSON.stringify(rendererData))
    GraphicDataStore.setProdcutsArr(rendererData);
    //Converting the div into the jqwidget list

    $("#subtab1").jqxListBox({ source: rendererData, displayMember:"title"});

}

HomePresenter.populateAssetsList = function(data){
    //Converting the div into the jqwidget list with the renderer for that list
    $("#assetDetails").jqxListBox('beginUpdate');
    $("#assetDetails").jqxListBox({ source: data, displayMember: "title", valueMember: "description", width: 200, height: 250,
        renderer: function (index, label, value) {
            var datarecord = data[index];
            var imgurl = datarecord.image;
            var img = '<img height="30" width="40" src="' + imgurl + '"/>';
            var table = '<table class="assestsJQList" style="min-width: 130px;"><tr><td style="width: 40px;" rowspan="1">' + img + '</td><td>' + datarecord.title +  '</td></tr></table>';
            return table;
        }
    });
    $("#assetDetails").jqxListBox('endUpdate');
    $('#assetDetails').jqxListBox('refresh');

    //This will say that the assets list item needs to be dragged and the drop taget will be assortment panels div
    $(".jqx-listitem-element").jqxDragDrop({ dropTarget: $('#subtab1'), revert: true });
    //This will add all the necessary events for d&d operation
    HomePresenter.addEventListeners();

}

HomePresenter.addEventListeners = function(){

    $('.jqx-listitem-element').bind('dropTargetEnter', function (event) {
        $(event.args.target).css('border', '2px solid #000');
        $(this).jqxDragDrop('dropAction', 'none');
    });
    $('.jqx-listitem-element').bind('dropTargetLeave', function (event) {
        $(event.args.target).css('border', '2px solid #aaa');
        $(this).jqxDragDrop('dropAction', 'default');
    });

    //Drag End
    $('.jqx-listitem-element').bind('dragEnd', function (event) {
        var existingItems = $("#subtab1").jqxListBox('getItems');
        var exists = HomePresenter.productAlreadyExists(existingItems,event.args.actualData.title);
        if(!exists){
            $("#subtab1").jqxListBox('addItem', { label: event.args.actualData.title} );
            $('#subtab1').css('border', '2px dashed #aaa');
            GraphicDataStore.addProdcut(event.args.actualData);//Yet to decide what fields exactly needs to be added to this object
        }
    });
    //Drag Start
    $('.jqx-listitem-element').bind('dragStart', function (event) {
        $('#subtab1').css('border', '2px solid #aaa');
        var items = $("#assetDetails").jqxListBox('getSelectedItems');
        $(this).jqxDragDrop('data', {
            actualData: items[0].originalItem
        });
    });
}

HomePresenter.productAlreadyExists = function(existingItems,newLabel){
    if(existingItems){
        for(var i=0; i< existingItems.length; i++){
            if(existingItems[i].label === newLabel){
                return true
            }
        }
    }
    return false;
}

HomePresenter.hideAssortPanel = function(){
    $('#assortPanel').hide();
    $('#dim').show();
}

HomePresenter.createProductsJSON = function(){
    var jsonData = {};
    var columnName = "products";
    jsonData[columnName] = GraphicDataStore.getProdcutsArr();
    var columnName = "id";
    jsonData[columnName] = GraphicDataStore.getCurrentAssortment().id;
    UpdateAssortment.update(GraphicDataStore.getCurrentAssortment(),jsonData,HomePresenter.hideAssortPanel);
}

HomePresenter.unHideAssortPanel = function(){
    $("#dim").hide();
    $("#assortPanel").show();
}