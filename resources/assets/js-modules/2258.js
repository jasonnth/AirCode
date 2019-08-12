__d(function(e,t,o,r){Object.defineProperty(r,"__esModule",{value:!0});var s=t(412),n=babelHelpers.interopRequireDefault(s),a=t(271),i=babelHelpers.interopRequireDefault(a);t(44);var l=t(379),u=babelHelpers.interopRequireDefault(l),p=t(773),h=babelHelpers.interopRequireDefault(p),d=t(890),b=babelHelpers.interopRequireDefault(d),f=t(2259),c=babelHelpers.interopRequireDefault(f),H=t(787),D=babelHelpers.interopRequireDefault(H),m=t(422),P=babelHelpers.interopRequireDefault(m),g=t(1352),R=babelHelpers.interopRequireDefault(g),v=t(781),q=babelHelpers.interopRequireDefault(v),y=t(1036),A=babelHelpers.interopRequireDefault(y),x=t(2261),L=babelHelpers.interopRequireDefault(x),I=t(2262),S=babelHelpers.extends({},b.default.propTypes,{issueId:n.default.number.isRequired,onFinish:n.default.func}),j=function(e){function t(o){babelHelpers.classCallCheck(this,t);var r=babelHelpers.possibleConstructorReturn(this,e.call(this,o));return r.onAddPhoto=r.onAddPhoto.bind(r),r.onDeletePhoto=r.onDeletePhoto.bind(r),r.state={photos:[],isLoading:!1,error:!1},r}return babelHelpers.inherits(t,e),t.prototype.componentDidMount=function(){var e=this;(0,I.fetchAttachmentsForIssue)(this.props.issueId).then(function(t){e.setState({photos:t})})},t.prototype.onAddPhoto=function(e){var t=this,o={maxWidth:1024,maxHeight:1024};(new L.default).pickPhoto(o,e).then(function(e){if(e){var o=t.state.photos;t.setState({isLoading:!0}),(0,I.uploadAttachment)(t.props.issueId,e).then(function(e){t.setState({photos:[].concat(babelHelpers.toConsumableArray(o),[e]),isLoading:!1,error:!1})}).catch(function(){t.setState({isLoading:!1,error:!0})})}})},t.prototype.onDeletePhoto=function(e){var t=this;this.setState({isLoading:!0}),(0,I.deleteAttachment)(e).then(function(){var o=t.state.photos.filter(function(t){return t.id!==e});t.setState({photos:o,isLoading:!1,error:!1})}).catch(function(){t.setState({isLoading:!1,error:!0})})},t.prototype.render=function(){var e=this,t=this.state,o=t.photos,r=t.isLoading,s=t.error;return i.default.createElement(b.default,this.props,babelHelpers.jsx(D.default,{contentInset:{bottom:A.default.contentInset.bottom}},void 0,babelHelpers.jsx(h.default,{title:u.default.phrase("Add photos",null,"The text in the title of photo uploader.")}),babelHelpers.jsx(c.default,{style:C.photoGrid,photos:o,isLoading:r,onAddPhoto:this.onAddPhoto,onDeletePhoto:this.onDeletePhoto})),babelHelpers.jsx(q.default,{toasts:this.state.toasts,withInsets:[A.default]},void 0,s&&babelHelpers.jsx(R.default,{error:!0,title:u.default.phrase("An error has occurred.",null,"The error message in the toast notification."),description:u.default.phrase("Please check your Internet connetion.",null,"The error message in the toast notification."),onClosePress:function(){return e.setState({error:!1})}},"error")),babelHelpers.jsx(A.default,{light:!0,onPrimaryPress:this.props.onFinish}))},t}(a.PureComponent);r.default=j,j.propTypes=S;var C=P.default.create(function(e){var t=e.size;return{photoGrid:{marginLeft:2*t.bp,marginRight:2*t.bp}}})},2258);