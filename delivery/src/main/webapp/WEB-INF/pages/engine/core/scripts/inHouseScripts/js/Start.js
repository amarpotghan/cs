$(document).ready(function() {
    EngineDataStore.setBaseURL("/delivery/pages/");
    getScreenMappingObject();
});

function getScreenMappingObject(){
    Router.forward(EngineDataStore.getBaseURL()+"graphics/tacks/screenMapping.json",true,function(json){
        parseScreenMappingObject(json);
    });
}

function parseScreenMappingObject(json){
    json=eval('(' + json + ')');
    EngineDataStore.setScreenMappingObject(json);
    $.each(json, function (key, item) {
        if(item.loadOnStartup == "true"){
            TemplateLoader.loadTemplate(key,'',item.containerId);
        }
    });
    getApiMappingObject();
}

function getApiMappingObject(){
    Router.forward(EngineDataStore.getBaseURL()+"graphics/tacks/RequestMapping.json",true,function(json){
        parseApiMappingObject(json);
    });
}

function parseApiMappingObject(json){
    json=eval('(' + json + ')');
    EngineDataStore.setApiMappingObject(json);
}

