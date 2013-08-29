var Router = function(){

}

Router.forward = function(url,async,callback){
    //Comment this and uncomment below while DEPLOYING
   /* if(url.indexOf("ocks") == -1){
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
*/
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

Router.loadRequest = function(key,async,callBack,params){
    //Comment this while DEPLOYING and uncomment belove
   /* if(params){

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
    }*/

    //Uncomment this while DEPLOYING and comment above
    if(params){
        Router.forward(EngineDataStore.getApiMappingObject()[key]+params,async,function(data){
            callBack(data);
        });
    }
    else{
        Router.forward(EngineDataStore.getApiMappingObject()[key],async,function(data){
            callBack(data);
        });
    }

}


/*
Router.forwardWithParams = function(url,path,type,callback){
    $.ajax({
        url:url,
        data:{path:path},
        dataType:'json',
        */
/* beforeSend: function(xhr){
         xhr.setRequestHeader('myName','rohan')
         },*//*

        type: type,
        success:function(result){
            callback(result);
        },
        error: function (error) {
            callback("error");
        }
    });

}*/
