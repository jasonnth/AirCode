__d(function(e,t,r,l){Object.defineProperty(l,"__esModule",{value:!0});var a=t(412),n=babelHelpers.interopRequireDefault(a),i=t(271),o=(babelHelpers.interopRequireDefault(i),t(44)),d=t(422),s=babelHelpers.interopRequireDefault(d),b=t(779),p=babelHelpers.interopRequireDefault(b),u={start:n.default.node,end:n.default.node,startEnabled:n.default.bool,endEnabled:n.default.bool,children:n.default.node,divider:p.default.propTypes.divider},f={startEnabled:!0,endEnabled:!0},c=function(e){function t(){return babelHelpers.classCallCheck(this,t),babelHelpers.possibleConstructorReturn(this,e.apply(this,arguments))}return babelHelpers.inherits(t,e),t.prototype.render=function(){var e=this.props,t=e.start,r=e.end,l=e.startEnabled,a=e.endEnabled,n=e.divider;return babelHelpers.jsx(p.default,{divider:n},void 0,babelHelpers.jsx(o.View,{style:h.container},void 0,babelHelpers.jsx(o.Text,{style:[h.range,h.start,!l&&h.disabled]},void 0,t),babelHelpers.jsx(o.View,{style:h.lineContainer},void 0,babelHelpers.jsx(o.View,{style:h.line})),babelHelpers.jsx(o.Text,{style:[h.range,h.end,!a&&h.disabled]},void 0,r)))},t}(i.PureComponent);l.default=c,c.defaultProps=f,c.propTypes=u;var h=s.default.create(function(e){var t=e.bp,r=e.color,l=e.font,a=Math.sqrt(6*t*(6*t)+7.5*t*(7.5*t)),n=9*t;return{container:{flexDirection:"row",alignItems:"center"},lineContainer:{position:"absolute",right:0,left:0,top:0,bottom:0,flex:1,alignItems:"center"},line:{width:0,height:n,borderLeftWidth:1,borderLeftColor:r.divider,backgroundColor:r.core.rausch,transform:[{rotate:"38.66deg"},{scale:a/n}]},range:l.title3,start:{},end:{position:"absolute",right:0,textAlign:"right"},disabled:{color:r.textMuted}}})},1382);