var Router = function(){

}

Router.forward = function(url,async,callback){

    //Comment this and uncomment below while DEPLOYING
    if(url.indexOf("ocks") == -1){
        callback("success");
    }
    else{
        $.ajax({
            url:url,
            async:async,
            success:function(result){
                callback(result);
            },
            error: function (error) {
                callback("error");
            }
        });
    }

    /*$.ajax({
        url:url,
        async:async,
        success:function(result){
            callback(result);
        },
        error: function (error) {
            callback("error");
        }
    });*/
}

Router.forwardWithParams = function(url,path,type,callback){
    $.ajax({
        url:url,
        data:{path:path},
        dataType:'json',
       /* beforeSend: function(xhr){
            xhr.setRequestHeader('myName','rohan')
        },*/
        type: type,
        success:function(result){
        callback(result);
        },
        error: function (error) {
            callback("error");
        }
    });

}

Router.loadTemplate = function(key,containerID){
    //This sets the default value for the containerElementID
    containerID = typeof containerID !== ('undefined'||"") ? containerID : "mainContainer";

    //Comment this and uncomment line below this while DEPLOYING
    Router.forward(EngineDataStore.getScreenMappingObject()[key].url,true,function(data){
        Router.designScreen(data,containerID);
    });

    /*Router.forward(EngineDataStore.getScreenMappingObject()[key].screenName,true,function(data){
     Router.designScreen(data,containerID);
     });*/

}


Router.loadRequest = function(key,async,callBack,params){
    //Comment this while DEPLOYING and uncomment belove
    if(params){

        switch(params){
            case "1":
                key = "getViewStructure1";
                break;
            case "2":
                key = "getViewStructure2";
                break;
            case "3":
                key = "getViewStructure3";
                break;
            case "Campaign-MasterPublication-Publication":
                key = "getTree1";
                break;
            case "MasterPublication-Campaign-Publication":
                key = "getTree2";
                break;
            case "Campaign-Publication-MasterPublication":
                key = "getTree3";
                break;
        }
        Router.forward(EngineDataStore.getApiMappingObject()[key],async,function(data){
            data=eval('(' + data + ')');
            callBack(data);
        });
    }
    else{
        Router.forward(EngineDataStore.getApiMappingObject()[key],async,function(data){
            callBack(data);
        });
    }

    //Uncomment this while DEPLOYING and comment above
   /* if(params){
        Router.forward(EngineDataStore.getApiMappingObject()[key]+params,async,function(data){
            callBack(data);
        });
    }
    else{
        Router.forward(EngineDataStore.getApiMappingObject()[key],async,function(data){
            callBack(data);
        });
    }*/

}

Router.designScreen = function(data,containerID){
    //Comment this while DEPLOYING
    data=eval('(' + data + ')');

    var placeHolderElement = document.getElementById(containerID);
    placeHolderElement.innerHTML = data.html;
 
    if(data.events){
        Router.attachEvents(data.events);
    }
    if(data.elements){
        Router.createElements(data.elements);
    }

}

Router.attachEvents = function(events){
    events=eval('(' + events + ')');
    for (var binding in events){
        HtmlEventDesigner.addEvents(events[binding].id,events[binding].event,events[binding].func,false);
    }
}

Router.createElements = function(elements){
    elements=eval('(' + elements + ')');
    for (var element in elements){
        HtmlElementDesigner.design(elements[element].id,elements[element].scriptName,elements[element].screenName);
    }

}


