__d(function(e,t,s,n){function r(){var e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:l,t=arguments.length>1&&void 0!==arguments[1]?arguments[1]:{},s=t.payload,n=t.meta;switch(t.type){case d.LOAD_FEEDBACKS:return(0,c.handle)(e,t,{start:function(e){return e.set("isFetchingFeedbacks",!0)},finish:function(e){return e.set("isFetchingFeedbacks",!1)},success:function(e){return e.setIn(["feedbacksByTripTemplateId",s.query.template_id],(0,i.List)(s.feedbacks))}});case d.LOAD_REVIEWS:return(0,c.handle)(e,t,{start:function(e){return e.set("isFetchingReviews",!0)},finish:function(e){return e.set("isFetchingReviews",!1)},success:function(e){return e.setIn(["reviewsByTripTemplateId",s.tripTemplateId],(0,i.List)(s.reviews))}});case d.LOAD_MORE_REVIEWS:return(0,c.handle)(e,t,{start:function(e){return e.set("isFetchingMoreReviews",!0)},finish:function(e){return e.set("isFetchingMoreReviews",!1)},success:function(e){return e.updateIn(["reviewsByTripTemplateId",s.tripTemplateId],function(e){return e.concat((0,i.List)(s.reviews))})}});case d.LOAD_SIMILAR_TRIP_TEMPLATES:return(0,c.handle)(e,t,{success:function(e){return e.setIn(["otherExperienceByTripTemplateId",s.tripTemplateId],s.otherExperience)}});case d.LOAD_TRIP_PRICE:return(0,c.handle)(e,t,{start:function(e){return e.set("isFetchingTripPrice",!0).set("loadTripPriceFailed",!1)},finish:function(e){return e.set("isFetchingTripPrice",!1)},success:function(e){return e.set("tripPrice",(0,i.Map)(s.tripPrice))},failure:function(e){return e.set("loadTripPriceFailed",!0)}});case d.LOAD_TRIP_TEMPLATES:return(0,c.handle)(e,t,{start:function(e){return e.set("isFetching",!0)},finish:function(e){return e.set("isFetching",!1)},success:function(e){return e.set("tripTemplates",(0,i.List)(s.tripTemplates))}});case d.LOAD_TRIP_TEMPLATE:return(0,c.handle)(e,t,{start:function(e){return e.set("isFetchingTemplate",!0)},finish:function(e){return e.set("isFetchingTemplate",!1)},success:function(e){return e.update("experiences",function(e){return e.concat(s.experiences)}).setIn(["tripTemplatesById",s.id],s).mergeIn(["experiencesById"],(0,u.default)(s.experiences,"id"))}});case d.LOAD_SCHEDULED_TRIP_TEMPLATES:return(0,c.handle)(e,t,{start:function(e){return e.set("isFetchingScheduledTemplates",!0)},finish:function(e){return e.set("isFetchingScheduledTemplates",!1)},success:function(e){return e.setIn(["scheduledTripTemplatesByTripTemplateId",s.query.template_id],s.scheduledTripTemplates)}});case d.SAVE_BOOKING_DATA:return e.set("bookingData",s);case d.SAVE_CUBA_TRAVEL_REASON:return e.update("bookingData",function(e){return babelHelpers.extends({},e,{travel_purpose:s})});case d.SAVE_CUBA_GUEST_ADDRESS:return e.update("bookingData",function(e){return babelHelpers.extends({},e,{guest_address:s})});case d.ADD_ADDITIONAL_TRAVELER:return e.update("additionalTravelers",function(e){return e.push(s)});case d.UPDATE_ADDITIONAL_TRAVELER:return e.setIn(["additionalTravelers",s.index],s.traveler);case d.REMOVE_ADDITIONAL_TRAVELER:return e.deleteIn(["additionalTravelers",s.index]);case d.REMOVE_ALL_ADDITIONAL_TRAVELERS:return e.set("additionalTravelers",(0,i.List)());case d.LOAD_FUTURE_RESERVATIONS:return(0,c.handle)(e,t,{start:function(e){return e.set("isFetchingFutureReservations",!0).set("loadFutureReservationsSuccess",!1)},finish:function(e){return e.set("isFetchingFutureReservations",!1)},success:function(e){return e.set("tripTemplates",(0,i.List)(s.tripTemplates)).set("loadFutureReservationsSuccess",!0).set("futureReservations",(0,i.List)(s.reservations))},failure:function(e){return e.set("loadFutureReservationsSuccess",!1)}});case d.SEND_MESSAGE_TO_HOST:return(0,c.handle)(e,t,{start:function(e){return e.set("isSendingMessage",!0)},finish:function(e){return e.set("isSendingMessage",!1)},success:function(e){return e.set("messageToHostResponse",s)}});case d.LOAD_USER:return(0,c.handle)(e,t,{start:function(e){return e.set("isFetchingUser",!0)},finish:function(e){return e.set("isFetchingUser",!1)},success:function(e){return e.setIn(["usersById",s.userId],s.user)},failure:function(e){return e.setIn(["loadUserFailedById",n.startPayload.userId],!0)}});case d.FETCH_WAITLIST:return(0,c.handle)(e,t,{success:function(e){return e.setIn(["showWaitlistsByTripTemplateId",s.query.template_id],0===s.waitlists.length)}});case d.SAVE_WAITLIST:return(0,c.handle)(e,t,{success:function(e){return e.setIn(["showWaitlistsByTripTemplateId",s.tripTemplateId],!1)}});case d.SOUND_TOGGLE:return e.set("soundOn",s.soundOn);default:return e}}Object.defineProperty(n,"__esModule",{value:!0}),n.default=r;var i=t(417),a=t(678),u=babelHelpers.interopRequireDefault(a),c=t(667),d=t(1571),l=(0,i.Map)({bookingData:{},feedbacks:null,isFetching:!1,isFetchingFeedbacks:!1,isFetchingMoreReviews:!1,isFetchingReviews:!1,isFetchingScheduledTemplates:!1,isFetchingTemplate:!1,isFetchingTripPrice:!1,isFetchingFutureReservations:!1,isFetchingUser:!1,isSendingMessage:!1,lastSyncedAt:null,loadUserFailedById:(0,i.Map)(),messageToHostResponse:null,otherExperienceByTripTemplateId:(0,i.Map)(),reviewsByTripTemplateId:(0,i.Map)(),similarTripTemplates:(0,i.List)(),tripPrice:(0,i.Map)(),tripTemplates:(0,i.List)(),tripTemplatesById:(0,i.Map)(),experiences:(0,i.List)(),experiencesById:(0,i.Map)(),scheduledTripTemplatesByTripTemplateId:(0,i.Map)(),showWaitlistsByTripTemplateId:(0,i.Map)(),feedbacksByTripTemplateId:(0,i.Map)(),loadTripPriceFailed:!1,additionalTravelers:(0,i.List)(),futureReservations:(0,i.List)(),loadFutureReservationsSuccess:!1,usersById:(0,i.Map)(),soundOn:!1})},1570);