__d(function(e,t,l,r){Object.defineProperty(r,"__esModule",{value:!0});var i=t(412),a=babelHelpers.interopRequireDefault(i),n=t(271),o=babelHelpers.interopRequireDefault(n),u=t(44),p=t(1070),s=t(422),b=babelHelpers.interopRequireDefault(s),d=t(758),f=babelHelpers.interopRequireDefault(d),h=babelHelpers.extends({},p.VictoryLabel.propTypes,{height:a.default.number.isRequired,width:a.default.number.isRequired,theme:a.default.object.isRequired}),c={textAnchor:"middle",verticalAnchor:"middle"},H=function(e){function t(){return babelHelpers.classCallCheck(this,t),babelHelpers.possibleConstructorReturn(this,e.apply(this,arguments))}return babelHelpers.inherits(t,e),t.prototype.render=function(){var e=this.props,t=e.width,l=e.height,r=e.theme,i=u.StyleSheet.flatten(y.label),a=r.chart.padding,n=t-a.left-a.right,s=l-a.bottom-a.top,b=a.left+n/2,d=a.top+s/2;return o.default.createElement(p.VictoryLabel,babelHelpers.extends({},(0,f.default)(this.props,p.VictoryLabel),{style:babelHelpers.extends({},i,{fill:i.color}),lineHeight:i.lineHeight/i.fontSize,x:b,y:d}))},t}(n.Component);r.default=H,H.propTypes=h,H.defaultProps=c;var y=b.default.create(function(e){return{label:e.font.regularMuted}})},1327);