__d(function(e,t,r,s){function i(e,t){return Math.abs(e.timestamp-t.timestamp)>=18e4}var a=t(412),n=babelHelpers.interopRequireDefault(a),l=t(271),p=babelHelpers.interopRequireDefault(l),o=t(44),u=t(2225),d=t(2284),m=n.default.object,b=n.default.object,c={item:m.isRequired,viewer:b.isRequired,sender:b.isRequired,thread:n.default.object.isRequired,prev:m,next:m},g={prev:null,next:null},f=function(e){function t(){return babelHelpers.classCallCheck(this,t),babelHelpers.possibleConstructorReturn(this,e.apply(this,arguments))}return babelHelpers.inherits(t,e),t.prototype.render=function(){var e=this.props,t=e.item,r=e.prev,s=e.next,a=e.viewer,n=e.sender,l=t.data,p=t.type,d=u.messagingRegistry.getItemComponent(p),m=n.id,b=a.id===m,c=!!r&&r.senderID===m,g=!!s&&s.senderID===m,f=null===s,R=null===r,v=f?M.finalMessageVerticalMarginStyle:M.MessageItemPropTypemessageVerticalMarginStyle,y=R||!c||i(t,r),I=!g||i(t,s);return babelHelpers.jsx(o.View,{style:[M.container,v]},void 0,babelHelpers.jsx(d,{item:t,data:l,prev:r,next:s,sender:n,isViewer:b,nextIsSameUser:g,prevIsSameUser:c,showTimestamp:y,isLastMessageInClump:I}))},t}(p.default.PureComponent);f.propTypes=c,f.defaultProps=g;var M=o.StyleSheet.create({container:{overflow:"hidden"},messageVerticalMarginStyle:{marginBottom:d.MESSAGE_VERTICAL_MARGIN},finalMessageVerticalMarginStyle:{marginBottom:d.FINAL_THREAD_MESSAGE_VERTICAL_MARGIN}});r.exports=f},2283);