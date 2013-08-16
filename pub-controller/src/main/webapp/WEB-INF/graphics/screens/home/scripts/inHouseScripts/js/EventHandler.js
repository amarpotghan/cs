
var rendererData;

$(document).bind("TREE_ITEM_CLICKED", function itemClickedHandler(e){
    rendererData = {"mydata":e.uiData};
    loadViewItems(rendererData, "/pub-controller/graphics/screens/home/htmls/renderers/TileViewRenderer.html");
});

function handleViewChange(evt){

    switch(evt.currentTarget.id)
    {
        case "tileView":
            //console.log(":: Load Tile View Button Clicked ::");
            loadViewItems(rendererData, "/pub-controller/graphics/screens/home/htmls/renderers/TileViewRenderer.html");
            break;

        case "listView":
            //console.log(":: Load List View Button Clicked ::");
            loadViewItems(rendererData, "/pub-controller/graphics/screens/home/htmls/renderers/ListViewRenderer.html");
            break;

        case "detailView":
            //console.log(":: Load Detail View Button Clicked ::");
            loadViewItems(rendererData, "/pub-controller/graphics/screens/home/htmls/renderers/DetailViewRenderer.html");
            break;
    }
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

function loadViewItems(evt, currentTemplateView){
    MustacheWrapper.createUI(currentTemplateView,evt, function(currentViewStr){
        $('#viewHolder').html(currentViewStr);
    });
}