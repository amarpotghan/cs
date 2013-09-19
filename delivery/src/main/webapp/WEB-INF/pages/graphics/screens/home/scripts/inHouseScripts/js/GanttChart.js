var GanttChart = function(){

    var currentPath;
    var parentNode;
    var newNode
    var currentRow;
    var dropTargetType

    var input;

    this.createGanttChart = function(id){
        TreeGrid({Layout:{Url:"../../../graphics/screens/home/scripts/inHouseScripts/js/Def.js"},
                  Data:{Script:"myData"}},id);
    }

    Grids.OnGetMenu = function(G,row,col){
        console.log(row)
        var possibleDim=[];
        possibleDim  = GraphicDataStore.getPossibleChild(row.type);
        var menuItems = [];
        var menu = {Items:menuItems};

        if(possibleDim != ""){
            for(var i=0; i< possibleDim.length; i++){
                //This is to create possible dimensions on click of currentNode
                var item = {};
                var keyName = "Name";
                item[keyName] = possibleDim[i];
                var keyName = "Text";
                item[keyName] = "Create "+possibleDim[i];
                menuItems.push(item);
            }

        }

        if(row.type != "root"){
            //This is to delete the currentNode
            var deleteItem = {};
            var keyName = "Name";
            deleteItem[keyName] = "Delete";
            var keyName = "Text";
            deleteItem[keyName] = "Delete";
            menuItems.push(deleteItem);
        }
        return menu;
    }


    Grids.OnRowDelete = function(grid,row,type){
        /*if(type == 1){
            var input=new Object();
            input.id=row.id;
            input.name=row.name;
            input.type=row.type;
            input.groupId=row.groupId;
            var prefix=getUrlPrefix(row.type,"delete");
            GanttChartPresenter.deleteDimension(prefix,row.type,input,GanttChart.onDeleteSuccess);
        }*/
    }

    GanttChart.onDeleteSuccess=function(){
        Grids[0].DeleteRow(currentRow,2);
    }

    Grids.OnContextMenu = function(G,row,col,name){
        currentRow = row;
        if(name == "Delete"){

            var r=confirm("Are you sure you want to delete "+ row.name+" ?");
            if (r==true)            {
                var input=new Object();
                input.id=row.id;
                input.name=row.name;
                input.type=row.type;
                input.groupId=row.groupId;
                var prefix=getUrlPrefix(row.type,"delete");
                GanttChartPresenter.deleteDimension(prefix,row.type,input,GanttChart.onDeleteSuccess);
            }

        }else{
             showPopUp(G,row,col,name);
        }
    }


    Grids.OnStartDrag = function(grid,row,col){
        //To suppress the dragging as per the dimension type
        if(row.type == "root") {
            return true;
        }
        return false;
    }

    Grids.OnCanDrop = function(grid,row,togrid,torow,type,copy){
        dropTargetType = GraphicDataStore.getPossibleDropParent(row.type);
        var dropTargetFound = $.inArray(torow.type, dropTargetType)
        if(dropTargetFound != -1){
            return 2;
        }
        return 0;

    }

    Grids.OnEndDrag = function(grid,row,togrid,torow,type,X,Y,copy){
       if(type === 2){
           var oldPathForChild = row.path;
           var newChildNode = row;
           var parentNode = torow;
           newChildNode.path = parentNode.path +","+parentNode.title;
           var newPathForChild = newChildNode.path;

           if(newPathForChild.indexOf("-1")==0)
               newPathForChild = newPathForChild.match(/([^,]*),(.*)/)[2];   //To remove -1 root folder

           var flag=isFolder(newChildNode.type);
           var prefix;
           prefix =getUrlPrefix(row.type,"move");
           prefix = prefix+row.type;
           GanttChartPresenter.dragAndDropDimensions(prefix,row,oldPathForChild,flag,newPathForChild,onDropSuccess);

       }
    }

    function onDropSuccess(data){
       /* if(draggedNode.data.type == "Assortment"){
            var cb = draggedNode.toDict(true, function(dict){
                //dict.title = "Copy of " + dict.title;
                delete dict.key; // Remove key, so a new one will be created
            });
            droppedSrcNode.addChild(cb);
        }
        else{
            draggedNode.move(droppedSrcNode, dragHitMode);
        }*/
    }

    function addNode(data){
        Grids[0].AddRow(currentRow,null,1);
        Grids[0].SetValue(currentRow.lastChild,"name",data.name,1);
        Grids[0].SetValue(currentRow.lastChild,"title",data.title,1);
        Grids[0].SetValue(currentRow.lastChild,"path",data.path,1);
        Grids[0].SetValue(currentRow.lastChild,"id",data.id,1);
        Grids[0].SetValue(currentRow.lastChild,"groupId",data.groupId,1);
        Grids[0].SetValue(currentRow.lastChild,"type",data.type,1);
        Grids[0].SetValue(currentRow.lastChild,"budgetOwner",data.budgetOwner,1);
        Grids[0].SetValue(currentRow.lastChild,"budget",data.budget,1);
        Grids[0].SetValue(currentRow.lastChild,"startDate",data.startDate,1);
        Grids[0].SetValue(currentRow.lastChild,"endDate",data.endDate,1);
        Grids[0].SetValue(currentRow.lastChild,"manager",data.manager,1);
        Grids[0].SetValue(currentRow.lastChild,"Items",data.Items,1);
        //Grids[0].Recalculate(currentRow,"startDate",1);
    }

    function showPopUp(G,row,col,name){
        $( "#dialog-form" ).dialog({
            height: 490,
            width: 500,
            modal: true,
            buttons: {
            "Create ": function() {

                var bValid = true;
                var dimensionName = $( "#name" ),
                    manager = $( "#manager" ),
                    startdate = $( "#startdate" ),
                    enddate = $( "#enddate" ),
                    budgetowner = $( "#budgetOwner" ),
                    budgetamount = $( "#budget"),
                    currency = $( "#currency" );

                input = new Object();
                input.name=dimensionName.val();
                input.managerName=manager.val();
                input.startDate=startdate.val();
                input.endDate=enddate.val();
                input.budgetOwner = budgetowner.val();
                /*input.currency = currency.val();                 */
                input.budget = budgetamount.val() + " " + currency.val();
                input.type = name;
                input.Items = [];
                console.log(input);
                if(input.name != null && input.name !=""){
                    parentNode = row;
                    if(parentNode.type == "root"){
                        currentPath = "-1";
                    }
                    else{
                        currentPath = parentNode.path+","+ parentNode.title;
                        if(currentPath.indexOf("-1")==0)
                            currentPath = currentPath.match(/([^,]*),(.*)/)[2];   //To remove -1 root folder
                    }

                    input.path=currentPath;

                    var flag = isFolder(name);
                    var prefix=getUrlPrefix(name,"create");
                    newNode = createNewRow(input.name,name,currentPath);
                    if(name == "Assortment"){
                        GanttChartPresenter.createAssortment(prefix,name,input.name,currentPath,flag,addNode);
                    }else{
                        GanttChartPresenter.createDimension(prefix,name,input,currentPath,flag,addNode);
                    }
                }

                $( this ).dialog( "close" );

            },
            Cancel: function() {
                $( this ).dialog( "close" );
            }
        },
        close: function() {
            //allFields.val( "" ).removeClass( "ui-state-error" );
            clearForm();
        },
        autoOpen :true,
        changeTitle: document.getElementById("ui-id-1").innerHTML="Create New " + name,
        changeLabel: document.getElementById("dimensionName").innerHTML=name + " Name",
        datePicker: $(function() {
                        $( '.datePicker' ).datepicker({
                            showOn: 'both',
                            buttonImage: 'calendar-icon.png',
                            buttonImageOnly: true,
                            //changeMonth: true,
                            changeYear: true,
                            showAnim: 'blind',
                            showButtonPanel: true
                        });
                    })
        });
    }

    function  createNewRow(name,type,path){
        var newRowNode = {
            "id": "",
            "title": name,
            "type": type,
            "path": path,
            "Items": []
        }
        return newRowNode;
    }

    function isFolder(dim){
        var flag =true;
        if(dim == "Page" || dim == "Assortment"){
            flag = false;
        }
        return flag;
    }

    var getUrlPrefix=function(type,action){
        switch(type){
            case "Chapter":
                return  "/delivery/chapter/"+action+"/";
            case "Page":
                return  "/delivery/page/"+action+"/";
            case "Assortment":
                return  "/delivery/assortment/"+action+"/";
        }
        return "/delivery/dimension/"+action+"/";
    }


}


function clearForm(form)
{
    $(":input", form).each(function()
    {
        var type = this.type;
        var tag = this.tagName.toLowerCase();
        if (type == 'text')
        {
            this.value = "";
        }
    });
};
