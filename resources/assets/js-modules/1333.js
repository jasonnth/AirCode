__d(function(e,r,o,l){Object.defineProperty(l,"__esModule",{value:!0});var t=r(271),n=(babelHelpers.interopRequireDefault(t),r(44)),s=r(553),i=r(422),a=babelHelpers.interopRequireDefault(i),u=r(910),c=babelHelpers.interopRequireDefault(u),b=r(772),p=babelHelpers.interopRequireDefault(b),d=function(e){function r(){return babelHelpers.classCallCheck(this,r),babelHelpers.possibleConstructorReturn(this,e.apply(this,arguments))}return babelHelpers.inherits(r,e),r.prototype.renderColors=function(e,r){var o=a.default.theme.color,l=o[e];return babelHelpers.jsx(n.View,{style:f.colorSection},void 0,babelHelpers.jsx(c.default,{title:e}),Object.keys(l).map(function(o){return babelHelpers.jsx(n.View,{style:[f.colorRow,{backgroundColor:l[o]}]},o,babelHelpers.jsx(n.Text,{style:[f.colorFont,r&&f.inverseColorFont]},void 0,s.colors[e][o].name))}))},r.prototype.render=function(){return babelHelpers.jsx(p.default,{title:"Colors",subtitle:"DLS Canon colors"},void 0,this.renderColors("core"),this.renderColors("regular"),this.renderColors("dark"),this.renderColors("accent",!0),this.renderColors("jellyfish"))},r}(t.PureComponent);l.default=d;var f=a.default.create(function(e){var r=e.size,o=e.font;return{colorSection:{flex:1,flexDirection:"column"},colorRow:{paddingHorizontal:r.baseRow.paddingHorizontal,paddingVertical:r.vertical.small},colorFont:o.largeInverse,inverseColorFont:o.large}})},1333);