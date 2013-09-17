var myData;

var GanttChartPresenter = function(){
    this.design = function(id){
        var treeData;

        $(document).bind("viewStructureLoaded", function onSchemaLoadedHandler(e){
			//This is to create folder as per first element of the current schema
			treeData = {};
            treeData = [{
                "id" : "-1" ,"type": 'root',
                "title" : GraphicDataStore.getFirstDimension(),
                "name" : GraphicDataStore.getFirstDimension(),
                "isFolder" : "true",
                "Items":[]
            }];
            myData = {
                Body: [treeData]
            };

            $(document).bind("treeDataLoaded", function onSchemaLoadedHandler(e){

                if(e.treeData != "error"){
                  treeData[0].Items = e.treeData;
                }
                myData = {
                    Body: [treeData]
                };

                var gantChart = ElementFactory.getGanttChart();
                gantChart.createGanttChart(id);
                $(document).unbind("treeDataLoaded");
            });
            GanttChartPresenter.getTree();
        });


        $( "#dialog-form" ).dialog({
            autoOpen: false

        });

    }
}

GanttChartPresenter.getInstance = function(){
    return new GanttChartPresenter();
}

GanttChartPresenter.getTree = function(){
    GetTree.get();
}

GanttChartPresenter.createDimension = function(prefix,action,name,currentPath,flag,callBack){
    CreateDimensions.createDim(prefix,action,name,currentPath,flag,callBack);
}

GanttChartPresenter.createAssortment = function(prefix,action,name,currentPath,flag,callBack){
    CreateAssortment.create(prefix,action,name,currentPath,flag,callBack);
}

GanttChartPresenter.deleteDimension = function(prefix,action,name,currentPath,flag,callBack){
    DeleteDimension.deleteDim(prefix,action,name,currentPath,flag,callBack);
}

GanttChartPresenter.dragAndDropDimensions = function(prefix,name,oldPath,flag,newPath,callBack){
    DragDimension.dragAndDropDimensions(prefix,name,oldPath,flag,newPath,callBack);
}
