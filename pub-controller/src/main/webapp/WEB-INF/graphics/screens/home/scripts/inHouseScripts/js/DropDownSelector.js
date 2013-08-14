var DropDownSelector = function(){

    this.design = function(id)
    {
        $(document).bind("schemaLoaded", function onSchemaLoadedHandler(e){
            //This is to set the current schema display
            if(!e.schemaChanged){
                var data = e.schemaData;
                var dropdownObj = document.getElementById(id);
                var dropdown = ElementFactory.getDropDown();
                dropdown.createDropDown(dropdownObj,data);
            }
        });
        HomeService.getAllSchemas();
    }

}

DropDownSelector.getInstance = function(){
    return new DropDownSelector();
}



