function HomeService(){

}

HomeService.getAllSchemas = function(){
    Router.loadRequest("getAllSchema",true,onSchemaSuccess);
}

this.onSchemaSuccess = function(data){
    data=eval('(' + data + ')');
    GraphicDataStore.setSchemaArray(data);
    GraphicDataStore.setDefaultSchema();
    GraphicDataStore.setSchemaLabel();
    $(document).trigger({
        type: "schemaLoaded",
        //schemaData: data,
        schemaData: GraphicDataStore.getSchemaArray(),
        schemaChanged: false
    });

}

HomeService.getTree = function(){
    //Uncomment actual implementation
    //Router.loadRequest("getTree",true,onTreeSuccess,GraphicDataStore.getCurrentSchema().name);

    /*
     * Comment the below code to stop data mockup, and work with actual server data
     * Section starts here
     */

    if(GraphicDataStore.getCurrentSchema().id == "1"){
        Router.loadRequest("getTree1",true,onTreeSuccess);
    }   else if(GraphicDataStore.getCurrentSchema().id == "2"){
        Router.loadRequest("getTree2",true,onTreeSuccess);
    }
    /*
     * Section Ends here
     */
}

this.onTreeSuccess = function(data){
    data=eval('(' + data + ')');
    $(document).trigger({
        type: "treeDataLoaded",
        treeData: data
    });
}

HomeService.changeSchema = function(schemaId){
    //Router.loadRequest("getSchema",true,onChangeSchemaSuccess,schemaId);

    /*
     * Comment the below code to stop data mockup, and work with actual server data
     * Section starts here
     */
    switch(schemaId)
    {
        case "1":
        {
            Router.loadRequest("getSchema1",true,onChangeSchemaSuccess);
            break;
        }
        case "2":
        {
            Router.loadRequest("getSchema2",true,onChangeSchemaSuccess);
            break;
        }
    }
    /*
     * Section Ends here
     */
}

this.onChangeSchemaSuccess = function(data){
    data=eval('(' + data + ')');
    GraphicDataStore.setCurrentSchema(data);
    GraphicDataStore.setSchemaLabel();
    $(document).trigger({
        type: "schemaLoaded",
        //schemaData: data,
        schemaData: GraphicDataStore.getSchemaArray(),//schemaArray,
        schemaChanged: true
    });
    $(document).trigger({
        type: "TREE_ITEM_CLICKED",
        uiData: ""
    });
}
