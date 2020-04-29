(function (document, $, Coral) {
	"use strict";

	$(document).on("foundation-contentloaded",function(e){
		setTimeout(function(){
			showHideTab($(".showHideTabSelector",e.target));
			showHideCTA($(".showHideCTA",e.target));
		},0);
	});

	$(document).on("change", ".showHideTabSelector" , function(e){
		showHideTab($(this));
	});	


	$(document).on("change", ".showHideCTA" , function(e){
		showHideCTA($(this));
	});	

function showHideTab(el) {
        var coralTab = $(".testTabs coral-tablist coral-tab");
         el.each(function(i, element) {
                if ($(element).val()=='tab1'){
                   			coralTab[1].show();
                            coralTab[2].hide();
                } else if($(element).val()=='tab2'){
                	coralTab[1].hide();
                            coralTab[2].show();

                }else {
                	coralTab[1].hide();
                     coralTab[2].hide();
                }
        })

    }

function showHideCTA(el){
    debugger;
	var coralTab = $(".testTabs coral-tablist coral-tab");
	el.each(function(i,element) {
		if($(element).prop('checked')){
			coralTab[3].show();
		}
		else{coralTab[3].hide();
		}
	})
}


})(document, Granite.$, Coral);


