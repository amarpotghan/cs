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

HomePresenter.slidePanel = function(evt){
    if (btnSelectionFlag==0){
        $("#typeHolder").html(evt.currentTarget.name);
        $("#panel").animate({right:'30px'},"slow",function(){
            HomePresenter.createTree();
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
    }
    HomePresenter.changeSelectedBtn(evt.currentTarget.id);
}


HomePresenter.clearList = function(){
    var contextMenusHolder = document.getElementById('menus');
    contextMenusHolder.innerHTML = "";
}

HomePresenter.loadViewItems = function(evt,currentTemplateView){
    MustacheWrapper.createUI(currentTemplateView,evt, function(currentViewStr){
        $('#viewHolder').html(currentViewStr);
    });
}

HomePresenter.btnFocus = function(btn){
    $('.tileBtnCSS').css("border","0px");
    $('.listBtnCSS').css("border","0px");
    $('.detailBtnCSS').css("border","0px");
    $(btn).css("border","1px solid black");
}

HomePresenter.createTree = function(){
    var urls = "../testdrive/mocks/tree/AssetTree.json";
    var treeObj = document.getElementById("assetsTree");
    var darkTree = ElementFactory.getLazyTree();
    darkTree.createTree(treeObj,urls);
}

HomePresenter.reset = function(){
    /*$("#btnMIM").css("background-image",EngineDataStore.getBaseURL()+"/delivery/pages/graphics/screens/home/images/icons/MIM.png");
     $("#btnPIM").css("background-image",EngineDataStore.getBaseURL()+"/delivery/pages/graphics/screens/home/images/icons/PIM.png");
     $("#btnMAM").css("background-image",EngineDataStore.getBaseURL()+"/delivery/pages/graphics/screens/home/images/icons/MAM.png");*/
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
    rendererData = {"mydata":e.uiData};
    if(e.nodeType == "Assortment"){
        HomePresenter.showAssortmentPanel(e.uiData);
    }else{
        HomePresenter.hideAssortPanel();
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

HomePresenter.showAssortmentPanel = function(rendererData){
    $("#dim").hide();
    $("#assortPanel").show();
    /*$("#subtab1").jqxListBox({ allowDrop: true, dropAction:'copy', source: rendererData, width: 200, height: 250});*/

    //This will say that the assets list item needs to br dragged
    $(".jqx-listitem-element").jqxDragDrop({ dropTarget: $('#subtab1'), revert: true });
    HomePresenter.addEventListeners();

}

HomePresenter.addEventListeners = function(){
    $('.jqx-listitem-element').mouseenter(function () {
        console.log("mouse entered");
        // $(this).children('.jqx-listitem-element-price').fadeTo(100, 0.9);
    });
    $('.jqx-listitem-element').mouseleave(function () {
        console.log("mouse left");
        // $(this).children('.jqx-listitem-element-price').fadeTo(100, 0);
    });
    $('.jqx-listitem-element').bind('dropTargetEnter', function (event) {
        $(event.args.target).css('border', '2px solid #000');
        $(this).jqxDragDrop('dropAction', 'none');
        console.log("dropTarget entered");
    });
    $('.jqx-listitem-element').bind('dropTargetLeave', function (event) {
        $(event.args.target).css('border', '2px solid #aaa');
        $(this).jqxDragDrop('dropAction', 'default');
        console.log("dropTarget left");
    });
    $('.jqx-listitem-element').bind('dragEnd', function (event) {
        console.log("dragEnd executed");
        console.log(event.args);
        $('#subtab1').css('border', '2px dashed #aaa');
        /* if (onCart) {
         addItem({ price: event.args.price, name: event.args.name });
         onCart = false;
         }*/
    });
    $('.jqx-listitem-element').bind('dragStart', function (event) {
        console.log("***********dragStart");
        console.log(event);
        /*var tshirt = $(this).find('.jqx-listitem-element-header').text(),
         price = $(this).find('.jqx-listitem-element-price').text().replace('Price: $', '');
         $('#subtab1').css('border', '2px solid #aaa');
         price = parseInt(price, 10);
         console.log($(this));*/
        $(this).jqxDragDrop('data', {
            price: "1000Rs",
            name: "Rohan here"
        });
    });
}

HomePresenter.hideAssortPanel = function(){
    $('#assortPanel').hide();
    $('#dim').show();
}

HomePresenter.populateAssetsList = function(data){
    $("#assetDetails").jqxListBox({ source: data, displayMember: "title", valueMember: "description", width: 200, height: 250,
        renderer: function (index, label, value) {
            var datarecord = data[index];
            var imgurl = datarecord.image;
            var img = '<img height="30" width="40" src="' + imgurl + '"/>';
            var table = '<table style="min-width: 130px;"><tr><td style="width: 40px;" rowspan="1">' + img + '</td><td>' + datarecord.title +  '</td></tr></table>';
            return table;
        }
    });

}
