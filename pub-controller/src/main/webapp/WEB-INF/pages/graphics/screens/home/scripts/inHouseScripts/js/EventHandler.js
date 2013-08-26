var rendererData;
var btnSelectionFlag = 0;

$(document).bind("TREE_ITEM_CLICKED", function itemClickedHandler(e){
    rendererData = {"mydata":e.uiData};
    if(e.nodeType == "Assortment"){
        showAssortmentPanel();
    }else{
        hideAssortPanel();
        loadViewItems(rendererData, EngineDataStore.getBaseURL()+"graphics/screens/home/htmls/renderers/TileViewRenderer.html");
        btnFocus(".tileBtnCSS");
    }

});

function handleViewChange(evt){

    switch(evt.currentTarget.id)
    {
        case "tileView":
            //console.log(":: Load Tile View Button Clicked ::");
            loadViewItems(rendererData, EngineDataStore.getBaseURL()+"graphics/screens/home/htmls/renderers/TileViewRenderer.html");
            btnFocus(".tileBtnCSS");
            break;

        case "listView":
            //console.log(":: Load List View Button Clicked ::");
            loadViewItems(rendererData, EngineDataStore.getBaseURL()+"graphics/screens/home/htmls/renderers/ListViewRenderer.html");
            btnFocus(".listBtnCSS");
            break;

        case "detailView":
            //console.log(":: Load Detail View Button Clicked ::");
            loadViewItems(rendererData, EngineDataStore.getBaseURL()+"graphics/screens/home/htmls/renderers/DetailViewRenderer.html");
            btnFocus(".detailBtnCSS");
            break;
    }
}

function clearList(){
    var contextMenusHolder = document.getElementById('menus');
    contextMenusHolder.innerHTML = "";
}

function btnFocus(btn){
    $('.tileBtnCSS').css("border","0px");
    $('.listBtnCSS').css("border","0px");
    $('.detailBtnCSS').css("border","0px");
    $(btn).css("border","1px solid black");
}

function getChildrenForSelectedNode(node){
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

function showAssortmentPanel(){
    $('#dim').hide();
    $('#assortPanel').show();
}

function loadViewItems(evt,currentTemplateView){
     MustacheWrapper.createUI(currentTemplateView,evt, function(currentViewStr){
     $('#viewHolder').html(currentViewStr);
     });
}

function hideAssortPanel(){
    $('#assortPanel').hide();
    $('#dim').show();
}

function slidePanel(evt){
    //console.log(event.currentTarget.id + "curre")

    if (btnSelectionFlag==0)
    {
       $("#typeHolder").html(evt.currentTarget.name);
       $("#assetsList").html(data.type[0].list);
       $("#assetDetails").html(data.type[0].details);
       $("#panel").animate({right:'30px'},"slow");
        btnSelectionFlag=1;
    }
    else if (btnSelectionFlag==1 && ($("#typeHolder").html()==evt.currentTarget.name) )
    {
        $("#panel").animate({right:'-200px'},"slow");
        reset();
        btnSelectionFlag=0;
    }
    else {
       $("#typeHolder").html(evt.currentTarget.name);
       $("#assetsList").html(data.type[0].list);
       $("#assetDetails").html(data.type[0].details);
    }
    changeSelectedBtn(evt.currentTarget.id);
}

function reset(){
    $("#btnMIM").css("background-image","url(/pub-controller/pages/graphics/screens/home/images/icons/MIM.png)");
    $("#btnPIM").css("background-image","url(/pub-controller/pages/graphics/screens/home/images/icons/PIM.png)");
    $("#btnMAM").css("background-image","url(/pub-controller/pages/graphics/screens/home/images/icons/MAM.png)");

}

function changeSelectedBtn(btnId){
    $('#btnMIM').css("background-image","url(/pub-controller/pages/graphics/screens/home/images/icons/MIM.png)");
    $('#btnPIM').css("background-image","url(/pub-controller/pages/graphics/screens/home/images/icons/PIM.png)");
    $('#btnMAM').css("background-image","url(/pub-controller/pages/graphics/screens/home/images/icons/MAM.png)");
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


var data = {
    "type": [
    {  "list": "This is PIM list. <br>This is PIM list. <br>This is PIM list. <br>This is PIM list.",
        "details": "These are PIM list details. These are PIM list details. These are PIM list details."
    },
    {   "list": "This is MAM list. <br>This is MAM list. <br>This is MAM list. <br>This is MAM list.",
        "details": "These are MAM list details. These are MAM list details. These are MAM list details."
    },
    {    "list": "This is MIM list. <br>This is MIM list. <br>This is MIM list. <br>This is MIM list.",
        "details": "These are MIM list details. These are MIM list details. These are MIM list details."
    }
]
}
