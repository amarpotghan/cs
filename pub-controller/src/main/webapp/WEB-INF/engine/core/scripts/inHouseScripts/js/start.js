$(document).ready(function() {
    getScreenMappingObject();
});

function getScreenMappingObject(){
    //Replace this for production /RefactoredPub/graphics/tacks/ScreenMapping.json
    Router.forward("/RefactoredPub/graphics/tacks/MocksScreenMapping.json",true,function(json){
        parseScreenMappingObject(json);
    });
}

function parseScreenMappingObject(json){
    json=eval('(' + json + ')');
    EngineDataStore.setScreenMappingObject(json);
    $.each(json, function (key, item) {
        if(item.loadOnStartup == "true"){
            Router.loadTemplate(key,item.containerId);
        }
    });
    getApiMappingObject();
}

function getApiMappingObject(){
    //Replace this for production /RefactoredPub/graphics/tacks/RequestMapping.json
    Router.forward("/RefactoredPub/graphics/tacks/MocksRequestMapping.json",true,function(json){
        parseApiMappingObject(json);
    });
}

function parseApiMappingObject(json){
    json=eval('(' + json + ')');
    EngineDataStore.setApiMappingObject(json);
}

