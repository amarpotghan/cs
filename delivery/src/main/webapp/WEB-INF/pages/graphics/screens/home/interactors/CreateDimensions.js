function CreateDimensions(){

}

CreateDimensions.createDim = function(prefix,action,input,currentPath,flag,callBack){
    Router.forwardWithPost(prefix+action+"/name/"+input.name+"/path/"+currentPath+"/folder/"+flag,true,input,function(data){
        callBack(data);
    });
}

