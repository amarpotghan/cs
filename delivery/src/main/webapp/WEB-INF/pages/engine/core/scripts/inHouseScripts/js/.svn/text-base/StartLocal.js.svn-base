$(document).ready(function() {
    EngineDataStore.setBaseURL("../../../");
    getScreenMappingObject();
});


function getScreenMappingObject(){
    Router.forward(EngineDataStore.getBaseURL()+"graphics/tacks/MocksScreenMappingLocal.json",true,function(json){
        parseScreenMappingObject(json);
    });
}
var arr = [];

function parseScreenMappingObject(json){
    //json=eval('(' + json + ')');
    EngineDataStore.setScreenMappingObject(json);
    $.each(json, function (key, item) {
        if(item.loadOnStartup == "true"){
            var obj = new Object();
            obj.key = key;
            obj.item = item;
            arr.push(obj);
            //TemplateLoader.loadTemplate(key,'',item.containerId);
        }
    });
    TemplateLoader.loadOnStartUp();

    getApiMappingObject();
}

function getApiMappingObject(){
    Router.forward(EngineDataStore.getBaseURL()+"graphics/tacks/MocksRequestMappingLocal.json",true,function(json){
        parseApiMappingObject(json);
    });
}

function parseApiMappingObject(json){
    //json=eval('(' + json + ')');
    EngineDataStore.setApiMappingObject(json);
}

