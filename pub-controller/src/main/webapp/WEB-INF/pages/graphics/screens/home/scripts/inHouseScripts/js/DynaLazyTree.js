var DynaLazyTree = function(){
    this.createTree = function(treeObj,urls){
            $(treeObj).dynatree({
                //children: data,
                initAjax: {
                    url:urls
                },
                onLazyRead: function(node){
                    node.appendAjax({
                        url: "/pub-controller/testdrive/mocks/tree/AssetChildTree.json",
                        success: function(node, data) {
                            alert(JSON.stringify(data))
                            // Called after nodes have been created and the waiting icon was removed.
                            // 'this' is the options for this Ajax request
                        }
                    });
                }
            });
    }

};

