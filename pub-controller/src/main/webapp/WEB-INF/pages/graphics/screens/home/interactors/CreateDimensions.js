function CreateDimensions(){

}

CreateDimensions.createDim = function(prefix,action,name,currentPath,flag,callBack){
    Router.forward(prefix+action+"/name/"+name+"/path/"+currentPath+"/folder/"+flag,true,function(data){
        callBack(data);
    });
}
