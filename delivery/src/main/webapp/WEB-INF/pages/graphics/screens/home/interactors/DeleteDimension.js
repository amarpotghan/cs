function DeleteDimension(){

}

DeleteDimension.deleteDim = function(prefix,type,input,callback){
    alert(prefix+type+"/"+input.name)
    alert(JSON.stringify(input));
    Router.forwardWithPost(prefix+type+"/"+input.name,true,input,callback);
}

