__d(function(e,r,s,n){function a(){var e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:t,r=arguments.length>1&&void 0!==arguments[1]?arguments[1]:{},s=r.type,n=r.payload,a=r.error,i=a?n:{};switch(s){case o.CREATE_FEEDBACKS_FOR_TRIP_INSTANCE:return babelHelpers.extends({},e,{tripInstanceId:n.tripInstanceId,feedbackByGuestProfileId:{}});case o.UPDATE_FEEDBACK_FOR_GUEST:var u=n.feedback,R=n.guestProfileId;return babelHelpers.extends({},e,{feedbackByGuestProfileId:babelHelpers.extends({},e.feedbackByGuestProfileId,babelHelpers.defineProperty({},R,babelHelpers.extends({},d,e.feedbackByGuestProfileId[R],u)))});case o.LOAD_FEEDBACK_REASONS:return(0,l.handle)(e,r,{start:function(){return babelHelpers.extends({},e,{isReviewReasonsLoading:!0,loadReviewReasonsError:null})},success:function(){var r=n.review_reasons,s=n.report_reasons;return babelHelpers.extends({},e,{isReviewReasonsLoading:!1,loadReviewReasonsError:null,reviewReasons:r,reportReasons:s})},failure:function(){return babelHelpers.extends({},e,{isReviewReasonsLoading:!1,loadReviewReasonsError:i})}});default:return e}}Object.defineProperty(n,"__esModule",{value:!0}),n.default=a;var l=r(667),o=r(1774),t={tripInstanceId:null,feedbackByGuestProfileId:null,isReviewReasonsLoading:!1,loadReviewReasonsError:null,reviewReasons:null,reportReasons:null},d={reviewReasonIds:[],reportReasonIds:[],details:null}},1776);