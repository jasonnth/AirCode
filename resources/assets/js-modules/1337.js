__d(function(e,r,t,l){Object.defineProperty(l,"__esModule",{value:!0});var i=r(412),n=babelHelpers.interopRequireDefault(i),s=r(271),a=babelHelpers.interopRequireDefault(s),u=r(44),p=r(828),o=babelHelpers.interopRequireDefault(p),b=r(1338),d=babelHelpers.interopRequireDefault(b),f=r(740),c=babelHelpers.interopRequireWildcard(f),H=r(787),h=babelHelpers.interopRequireDefault(H),R=r(1348),m=babelHelpers.interopRequireDefault(R),q=r(1349),v=babelHelpers.interopRequireDefault(q),y=r(811),D=babelHelpers.interopRequireDefault(y),j=r(410),x=babelHelpers.interopRequireDefault(j),C=u.Dimensions.get("window"),g=C.width,E=C.height,S=function(e,r,t){return"https://unsplash.it/"+e+"/"+r+"?image="+t},_=function(e){function r(){return babelHelpers.classCallCheck(this,r),babelHelpers.possibleConstructorReturn(this,e.apply(this,arguments))}return babelHelpers.inherits(r,e),r.prototype.render=function(){var e=this.props,r=e.id,t=e.onPress;return babelHelpers.jsx(u.TouchableWithoutFeedback,{onPress:t},void 0,babelHelpers.jsx(u.View,{style:P.container},void 0,babelHelpers.jsx(m.default,{type:"el",typeId:r},void 0,babelHelpers.jsx(u.Image,{style:P.element,source:(0,D.default)(S(g,E,r))}))))},r}(a.default.Component);_.propTypes={id:n.default.number.isRequired,onPress:n.default.func.isRequired};var T=function(e){return x.default.push(c.SCREENS_SHARED_ELEMENTS_TO,{id:e},{transitionGroup:""+e})},w=function(e){function r(){return babelHelpers.classCallCheck(this,r),babelHelpers.possibleConstructorReturn(this,e.apply(this,arguments))}return babelHelpers.inherits(r,e),r.prototype.render=function(){var e=[1051,1060,1050,1039,1030];return babelHelpers.jsx(h.default,{},void 0,babelHelpers.jsx(o.default,{image:S(g,320,0),title:"Shared Element Transitions",subtitle:"Tap on an item"}),babelHelpers.jsx(d.default,{},void 0,e.map(function(e){return babelHelpers.jsx(v.default,{id:""+e},e,babelHelpers.jsx(_,{id:e,onPress:function(){return T(e)}}))})))},r}(a.default.Component);l.default=w;var P=u.StyleSheet.create({element:{width:g/3,height:E/3},container:{marginVertical:24,marginLeft:8}})},1337);