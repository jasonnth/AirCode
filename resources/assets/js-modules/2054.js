__d(function(e,t,r,o){Object.defineProperty(o,"__esModule",{value:!0});var n=t(412),a=babelHelpers.interopRequireDefault(n),s=t(271),l=babelHelpers.interopRequireDefault(s),i=t(44),u=t(924),d=babelHelpers.interopRequireDefault(u),p=t(759),f=babelHelpers.interopRequireDefault(p),c=t(379),h=babelHelpers.interopRequireDefault(c),b=t(654),E=t(1352),m=babelHelpers.interopRequireDefault(E),y=t(790),g=t(684),T=babelHelpers.interopRequireWildcard(g),_=t(789),C=babelHelpers.interopRequireDefault(_),A=t(2040),H=["none","NONE"],D=function(e){return H.includes(e)},L={children:a.default.any,toasts:a.default.object.isRequired,onFetchedEmptyGuidebookActionPress:a.default.func,onLayoutWindow:a.default.func,onRetryFetch:a.default.func,removeToast:a.default.func.isRequired},v={onLayoutWindow:function(){},onRetryFetch:function(){}},F=function(e){function t(r){babelHelpers.classCallCheck(this,t);var o=babelHelpers.possibleConstructorReturn(this,e.call(this,r));return o.handleConnectionInfoChange=o.handleConnectionInfoChange.bind(o),o.handleLayout=o.handleLayout.bind(o),o}return babelHelpers.inherits(t,e),t.prototype.componentDidMount=function(){i.NetInfo.addEventListener("change",this.handleConnectionInfoChange)},t.prototype.componentWillUnmount=function(){i.NetInfo.removeEventListener("change",this.handleConnectionInfoChange)},t.prototype.handleConnectionInfoChange=function(e){if(!D(e)){var t=this.props.toasts;t.size<1||t.get(A.FETCH_FAILED_TOAST,!1)&&this.props.onRetryFetch()}},t.prototype.handleLayout=function(e){var t=(0,f.default)(e.nativeEvent.layout,["width","height"]);(0,d.default)(t,this.lastWindowLayout)||this.props.onLayoutWindow(t),this.lastWindowLayout=t},t.prototype.render=function(){var e=this.props,t=e.children,r=e.onFetchedEmptyGuidebookActionPress,o=e.onRetryFetch,n=e.removeToast;return babelHelpers.jsx(i.View,{style:i.StyleSheet.absoluteFill,onLayout:this.handleLayout},void 0,babelHelpers.jsx(i.View,{style:i.StyleSheet.absoluteFill},void 0,t),babelHelpers.jsx(C.default,{},void 0,babelHelpers.jsx(m.default,{error:!0,action:h.default.phrase("Retry",null,"a button to retry after connection error"),id:A.FETCH_FAILED_TOAST,title:h.default.phrase("Error",null,"title of an error message"),description:h.default.phrase("Unfortunately, a connection error prevented your request from being sent. Please try again.",null,"description of an error message"),onActionPress:o}),babelHelpers.jsx(m.default,{error:!0,id:A.CREATE_BOOKMARK_FAILED_TOAST,title:h.default.phrase("Something went wrong",null,"title of an error message"),description:h.default.phrase("Failed to create bookmark",null,"description of an error message")}),babelHelpers.jsx(m.default,{error:!0,id:A.REMOVE_BOOKMARK_FAILED_TOAST,title:h.default.phrase("Something went wrong",null,"title of an error message"),description:h.default.phrase("Failed to remove bookmark",null,"description of an error message")}),r&&babelHelpers.jsx(m.default,{error:!0,action:h.default.phrase("Go back",null,"button to show previous results"),id:A.FETCHED_EMPTY_GUIDEBOOK,title:h.default.phrase("You left the city",null,"title of an error message"),description:h.default.phrase("A guidebook is not available in this region.",null,"description of error message"),onActionPress:function(){r(),n(A.FETCHED_EMPTY_GUIDEBOOK)}}),babelHelpers.jsx(m.default,{error:!0,id:A.CREATE_SCHEDULED_PLACE_FAILED_TOAST,title:h.default.phrase("Something went wrong",null,"title of an error message"),description:h.default.phrase("Failed to add this place to your itinerary.",null,"description of an error message")}),babelHelpers.jsx(m.default,{id:A.CREATE_SCHEDULED_PLACE_SUCCEEDED_TOAST,title:h.default.phrase("Great!",null,"title of a success message"),description:h.default.phrase("This place was added to your itinerary.",null,"description of an success message")}),babelHelpers.jsx(m.default,{error:!0,id:A.CREATE_PLACE_MEETUP_FAILED_TOAST,title:h.default.phrase("Something went wrong",null,"title of an error message"),description:h.default.phrase("Failed to add this meetup to your itinerary.",null,"description of an error message")}),babelHelpers.jsx(m.default,{id:A.CREATE_PLACE_MEETUP_SUCCEEDED_TOAST,title:h.default.phrase("Great!",null,"title of a success message"),description:h.default.phrase("This meetup was added to your itinerary.",null,"description of an success message")}),babelHelpers.jsx(m.default,{error:!0,id:A.UNJOIN_PLACE_MEETUP_FAILED_TOAST,title:h.default.phrase("Something went wrong",null,"title of an error message"),description:h.default.phrase("Failed to remove this meetup from your itinerary.",null,"description of an error message")}),babelHelpers.jsx(m.default,{id:A.UNJOIN_PLACE_MEETUP_SUCCEEDED_TOAST,title:h.default.phrase("Great!",null,"title of a success message"),description:h.default.phrase("This meetup was removed from your itinerary.",null,"description of an success message")})))},t}(l.default.Component);F.propTypes=L,F.defaultProps=v,o.default=(0,y.connect)(function(e){return{toasts:e.toasts}},function(e){return(0,b.bindActionCreators)({removeToast:T.removeToast},e)})(F)},2054);