function CreateDimensions(){

}

<<<<<<< HEAD:pub-controller/src/main/webapp/WEB-INF/graphics/screens/home/services/Dimensions.js
Dimensions.createDim = function(prefix,action,name,currentPath,flag,callBack){
=======
CreateDimensions.createDim = function(prefix,action,name,currentPath,flag,callMe){
>>>>>>> 242fa02ac23fd67896cee766432519ad5daae254:pub-controller/src/main/webapp/WEB-INF/pages/graphics/screens/home/interactors/CreateDimensions.js
    Router.forward(prefix+action+"/name/"+name+"/path/"+currentPath+"/folder/"+flag,true,function(data){
        callBack(data);
    });
}
