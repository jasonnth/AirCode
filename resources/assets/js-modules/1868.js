__d(function(e,r,n,t){function l(){var e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:a,r=arguments.length>1&&void 0!==arguments[1]?arguments[1]:{},n=r.payload;switch(r.type){case u.GET_GIFT_CARD_REDEMPTION_ASSETS:return(0,i.handle)(e,r,{start:function(e){return e.set("isFetching",!0).set("errorMessage",null).set("errorDetails",null)},finish:function(e){return e.set("isFetching",!1)},failure:function(e){return e.set("errorMessage",n.error_message).set("errorDetails",n.error_details)},success:function(e){return e.merge({landingScreenImage:n.landing_screen_image_url,landingScreenGreeting:n.landing_screen_greeting,videoUrl:n.video_url,videoThumbnailImageUrl:n.video_thumbnail_image_url,overlayUrl:n.overlay_url,recipientName:n.recipient_name,senderName:n.sender_name,amount:n.amount,currency:n.currency,recipientMessage:n.recipient_message,errorMessage:null,errorDetails:null})}});case u.UPDATE_GIFT_CREDIT_REDEEM:return(0,i.handle)(e,r,{start:function(e){return e.set("isFetching",!0).set("updateGiftCreditRequestSucceeded",!1).set("errorMessage",null).set("errorDetails",null)},finish:function(e){return e.set("isFetching",!1)},failure:function(e){return e.set("updateGiftCreditRequestSucceeded",!1).set("errorMessage",n.error_message).set("errorDetails",n.error_details)},success:function(e){return e.set("updateGiftCreditRequestSucceeded",!0).set("errorMessage",null).set("errorDetails",null)}});default:return e}}Object.defineProperty(t,"__esModule",{value:!0}),t.default=l;var i=r(667),s=r(417),u=r(1867),a=new s.Map({isFetching:!1,updateGiftCreditRequestSucceeded:!1,landingScreenImage:null,landingScreenGreeting:null,videoUrl:null,videoThumbnailImageUrl:null,overlayUrl:null,recipientName:null,senderName:null,amount:null,currency:null,recipientMessage:null,errorMessage:null,errorDetails:null})},1868);