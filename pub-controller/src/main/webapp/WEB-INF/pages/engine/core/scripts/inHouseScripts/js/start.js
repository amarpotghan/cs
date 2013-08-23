$(document).ready(function() {
    EngineDataStore.setBaseURL("/pub-controller/pages/");
    getScreenMappingObject();
});

function getScreenMappingObject(){
    Router.forward(EngineDataStore.getBaseURL()+"graphics/tacks/MocksScreenMapping.json",true,function(json){
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
    Router.forward(EngineDataStore.getBaseURL()+"graphics/tacks/MocksRequestMapping.json",true,function(json){
        parseApiMappingObject(json);
    });
}

function parseApiMappingObject(json){
    json=eval('(' + json + ')');
    EngineDataStore.setApiMappingObject(json);
}
