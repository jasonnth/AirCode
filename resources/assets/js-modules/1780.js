__d(function(e,r,t,a){function n(){var e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:f,r=arguments.length>1&&void 0!==arguments[1]?arguments[1]:{},t=r.type,a=r.payload;switch(t){case d.LOAD_PAYOUTS:case d.LOAD_REVIEWS:case d.LOAD_SCHEDULE:return(0,u.handle)(e,r,{success:function(){var r=(0,c.default)(a.rawTripTemplates.map(function(e){return e.experiences})),t=r.map(function(e){return(0,p.formatExperienceTemplate)(e,{detailsFetched:!1})});return babelHelpers.extends({},e,(0,i.default)(t,"id"))}});case d.ADD_TRIP_INSTANCE:case d.LOAD_TRIP_INSTANCE:return(0,u.handle)(e,r,{success:function(){var r=a.rawTripInstance.template.experiences,t=r.map(function(e){return(0,p.formatExperienceTemplate)(e,{detailsFetched:!0})});return babelHelpers.extends({},e,(0,i.default)(t,"id"))}});case d.LOAD_STATS:return(0,u.handle)(e,r,{success:function(){var r=a.rawLastReview;if(r){var t=r.template.experiences,n=t.map(function(e){return(0,p.formatExperienceTemplate)(e,{detailsFetched:!1})});return babelHelpers.extends({},e,(0,i.default)(n,"id"))}return e}});case d.UPDATE_EXPERIENCE_INSTANCE:return(0,u.handle)(e,r,{success:function(){var r=a.rawExperienceInstance.experience,t=(0,p.formatExperienceTemplate)(r,{detailsFetched:!0});return babelHelpers.extends({},e,babelHelpers.defineProperty({},t.id,t))}});default:return e}}Object.defineProperty(a,"__esModule",{value:!0}),a.default=n;var s=r(765),c=babelHelpers.interopRequireDefault(s),l=r(678),i=babelHelpers.interopRequireDefault(l),u=r(667),p=r(1773),d=r(1774),f={}},1780);