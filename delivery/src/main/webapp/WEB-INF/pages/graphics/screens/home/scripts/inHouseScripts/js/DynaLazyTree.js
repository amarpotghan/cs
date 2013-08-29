var DynaLazyTree = function(){
    this.createTree = function(treeObj,urls){
            $(treeObj).dynatree({
                //children: data,
                initAjax: {
                    url:urls
                },
                onLazyRead: function(node){
                    node.appendAjax({
                        url: "/delivery/testdrive/mocks/tree/AssetChildTree.json",
                        success: function(node, data) {
                            populateAssetsList(data);
                        }
                    });
                }
            });
    }

};

