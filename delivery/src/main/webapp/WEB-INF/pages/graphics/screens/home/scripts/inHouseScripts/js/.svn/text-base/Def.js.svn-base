TreeGridLoaded ( // JSONP header, to be possible to load from xxx_Jsonp data source

    {
        Cfg : {
            MainCol : "name",
            MaxHeight : "10",
            MinHeight:"10",
            ShowDeleted: "0",
            Style:"ExtJS"
        } ,

        LeftCols: [
             { Name:"name",Type:"Text", CanEdit:0, Width:"500",WidthPad:"17"}

        ],

        Cols : [
            { Name:"Panel", Visible:0, Type:"Panel",WidthPad:"17", Delete:0}
        ],

        RightCols: [
            { Name:"manager", Visible:0, Width:"75", Type:"Text", CanEdit:0,WidthPad:"17"},
            { Name:"budgetOwner", Visible:0, Width:"75", Type:"Text", CanEdit:0,WidthPad:"17"},
            { Name:"budget", Visible:0, Width:"75", Type:"Text", CanEdit:0,WidthPad:"17"},
            { Name:"startDate", Visible:0, Width:"75", Type:"Date", Format:"dd/MMM/yyyy", CanEdit:0,WidthPad:"17"},
            { Name:"endDate", Visible:0, Width:"75", Type:"Date", Format:"dd/MMM/yyyy", CanEdit:0,WidthPad:"17"},
            {   Name:"ganttChart", Visible:0, Type:"Gantt",GanttStart:"startDate",GanttEnd:"endDate",
                    GanttSlack:"Slack",GanttEdit:0,
                GanttFormatObject:"MainLeft",
                GanttFormat:"*name*",
                GanttMainTip:"<div style='padding-bottom:5px;'>Name : *name*</div>" +
                             "<div style='padding-bottom:5px;font-weight:bold;'>*startDate* - *endDate*</div>" +
                             "<div style='padding-bottom:5px;'>Manager : *manager*</div>" +
                             /*"<div style='padding-bottom:5px;'>Budget Owner : *budgetOwner*</div>*/
                            "<div>Budget : *budget*</div>",
                GanttZoom:"months",
                GanttClass:"GanttOrange",
                GanttZoomList:[


            { Name:'years', GanttUnits:'M', GanttChartRound:'y', GanttWidth:65,
                GanttHeader1:'y#yyyy', GanttHeader2:'M#MMM',
                GanttBackground:'M#1/1/2008#5',GanttRight:"0", GanttLeft:"0"
            },

            { Name:'months', GanttUnits:'w', GanttChartRound:'M', GanttWidth:170,
                GanttHeader1:'M#MMMM yyyy', GanttHeader2:'w#Week ddddddd', GanttHeader3:'d#dd',
                GanttBackground:'w#1/6/2008#5',GanttRight:"0", GanttLeft:"0"
            },

            { Name:'weeks', GanttUnits:'d', GanttChartRound:'w', GanttWidth:100,
                GanttHeader1:'w#MMMM yyyy Week ddddddd', GanttHeader2:'d#dddd',
                GanttBackground:'d#1/1/2008#5'
            },

            { Name:'days', GanttUnits:'h', GanttChartRound:'d', GanttWidth:30,
                GanttBackground:'h#',
                GanttHeader1:'d#dddd d MMMM yyyy', GanttHeader2:'h#HH'
            }

                ]

            }

        ],

        Header:{name:"Dimensions",startDate:"StartDate",endDate:"EndDate" ,manager:"Manager",budgetOwner:"Budget Owner",
                budget:"Budget"},

        Pagers: [
                    {Name:"Year", Visible:0,Caption:"Year",Type:"Gantt",PageSize:"y",ChartSize:"0",Left:"0",
                        Format:"yyyy",Width:"35",ZoomToPage:"2",GanttZoom:"years"},
                    {Name:"Month", Visible:0,Caption:"Month",Type:"Gantt",PageSize:"M",ChartSize:"y",Left:"0",
                        Format:"MMM",Width:"50",ZoomToPage:"10",GanttZoom:"months"},
                    {Name:"Pager", Visible:0,Caption:"Pager",Type:"Gantt",PageSize:"w",ChartSize:"y",Left:"0",
                        Format:"weekddddddd",Width:"60",ZoomToPage:"10",GanttZoom:"weeks"}
        ],

/*        Solid:[
            {
                Group:[
                    {
                        id:"Group", Space:"0", Panel:"0", Cells:"Zoom", CanPrint:"5", ListLeft:"2",
                        ZoomType:"SelectGanttZoom", ZoomLeft:'2', ZoomWidth:'170', ZoomPrintHPage:'1'
                    }

                ]
            }

        ]*/

        Toolbar: {Cells:""/*id:"Group", Space:"0", Panel:"0", Cells:"Zoom", CanPrint:"0", ListLeft:"0",
            ZoomType:"SelectGanttZoom", ZoomLeft:'2', ZoomWidth:'170', ZoomPrintHPage:'1'*/}
    }
)