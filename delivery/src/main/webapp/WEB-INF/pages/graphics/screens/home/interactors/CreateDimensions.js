function CreateDimensions(){

}

CreateDimensions.createDim = function(prefix,action,name,currentPath,flag,callBack){
    //if action assortmrnt
    if(action == "Assortment"){
        Router.forwardWithPost(prefix+name+"/"+currentPath,true,function(data){
            callBack(data);
        });
    }else{
        Router.forward(prefix+action+"/name/"+name+"/path/"+currentPath+"/folder/"+flag,true,function(data){
            callBack(data);
        });
    }

}

