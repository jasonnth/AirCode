__d(function(e,r,t,o){Object.defineProperty(o,"__esModule",{value:!0});var n=r(271),i=(babelHelpers.interopRequireDefault(n),r(834)),a=r(44),l=r(1191),u=babelHelpers.interopRequireDefault(l),s=r(422),p=babelHelpers.interopRequireDefault(s),b={numDots:i.Types.number.isRequired,activeIndex:i.Types.number.isRequired},d=function(e){function r(){return babelHelpers.classCallCheck(this,r),babelHelpers.possibleConstructorReturn(this,e.apply(this,arguments))}return babelHelpers.inherits(r,e),r.prototype.renderDot=function(e,r,t){return babelHelpers.jsx(a.View,{style:[e.dot,t&&e.dotActive]},r)},r.prototype.render=function(){var e=this,r=this.props,t=r.numDots,o=r.activeIndex;return babelHelpers.jsx(a.View,{style:c.container},void 0,(0,u.default)(t).map(function(r){return e.renderDot(c,r,r===o)}))},r}(n.Component);o.default=d,d.propTypes=b;var c=p.default.create(function(e){var r=e.bp,t=e.color;return{container:{justifyContent:"center",flexDirection:"row"},dot:{width:5,height:5,borderRadius:.5*r,margin:.5*r,borderColor:"#aaaaaa",borderWidth:1,backgroundColor:"#aaaaaa"},dotActive:{backgroundColor:t.white,borderColor:t.white}}})},1582);