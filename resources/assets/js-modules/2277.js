__d(function(e,t,l,r){var i=t(410),s=babelHelpers.interopRequireDefault(i),u=t(412),o=babelHelpers.interopRequireDefault(u),a=t(271),n=babelHelpers.interopRequireDefault(a),d=t(2278),p=babelHelpers.interopRequireDefault(d),f=function(e){return e.messaging},b={type:o.default.string.isRequired,id:o.default.oneOfType([o.default.string,o.default.number]).isRequired,showInputBar:o.default.bool,title:o.default.string.isRequired,subtitle:o.default.string,shouldLoadOlderItems:o.default.bool,listViewHeader:o.default.func,listViewFooter:o.default.func},c={showInputBar:!0,title:"Chat",subtitle:null,shouldLoadOlderItems:!1,listViewHeader:function(){},listViewFooter:function(){}},h=function(e){function t(){return babelHelpers.classCallCheck(this,t),babelHelpers.possibleConstructorReturn(this,e.apply(this,arguments))}return babelHelpers.inherits(t,e),t.prototype.render=function(){var e=this.props,t=e.id,l=e.type,r=e.showInputBar,i=e.title,u=e.subtitle,o=e.shouldLoadOlderItems,a=e.listViewHeader,n=e.listViewFooter;return babelHelpers.jsx(s.default.Screen,{barType:s.default.Screen.BAR_TYPE.STATIC,title:u?i+": "+u:i},void 0,babelHelpers.jsx(p.default,{selector:f,id:t,type:l,showInputBar:r,shouldLoadOlderItems:o,listViewHeader:a,listViewFooter:n}))},t}(n.default.Component);h.propTypes=b,h.defaultProps=c,l.exports=h},2277);