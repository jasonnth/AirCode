__d(function(e,t,n,r){"use strict";var o=t(352),i=t(354),s=t(355),a=t(347),p=t(60),l=t(356),u=t(44),c=t(179),d=u.Animated,h=(u.Platform,u.StyleSheet),b=u.View,f=p.PropTypes,v=function(e){function t(){var n,r,o;babelHelpers.classCallCheck(this,t);for(var s=arguments.length,a=Array(s),p=0;p<s;p++)a[p]=arguments[p];return n=r=babelHelpers.possibleConstructorReturn(this,e.call.apply(e,[this].concat(a))),r._renderLeft=function(e){return r._renderSubView(e,"left",r.props.renderLeftComponent,i.forLeft)},r._renderTitle=function(e){return r._renderSubView(e,"title",r.props.renderTitleComponent,i.forCenter)},r._renderRight=function(e){return r._renderSubView(e,"right",r.props.renderRightComponent,i.forRight)},o=n,babelHelpers.possibleConstructorReturn(r,o)}return babelHelpers.inherits(t,e),t.prototype.shouldComponentUpdate=function(e,t){return l.shouldComponentUpdate.call(this,e,t)},t.prototype.componentDidMount=function(){this._tvEventHandler=new c,this._tvEventHandler.enable(this,function(e,t){t&&"menu"===t.eventType&&e.props.onNavigateBack&&e.props.onNavigateBack()})},t.prototype.componentWillUnmount=function(){this._tvEventHandler&&(this._tvEventHandler.disable(),delete this._tvEventHandler)},t.prototype.render=function(){var e=this,t=this.props,n=t.scenes,r=t.style,o=t.viewProps,i=n.map(function(t){var n=a.extractSceneRendererProps(e.props);return n.scene=t,n}),s=this.props.statusBarHeight instanceof d.Value?d.add(this.props.statusBarHeight,new d.Value(56)):56+this.props.statusBarHeight;return p.createElement(d.View,babelHelpers.extends({style:[g.appbar,{height:s},r]},o),i.map(this._renderLeft,this),i.map(this._renderTitle,this),i.map(this._renderRight,this))},t.prototype._renderSubView=function(e,t,n,r){var o=e.scene,i=e.navigationState,s=o.index,a=o.isStale,p=o.key,l=i.index-s;if(Math.abs(l)>2)return null;var u=babelHelpers.extends({},e,{onNavigateBack:this.props.onNavigateBack}),c=n(u);if(null===c)return null;var h=0!==l||a?"none":"box-none";return babelHelpers.jsx(d.View,{pointerEvents:h,style:[g[t],{marginTop:this.props.statusBarHeight},r(e)]},t+"_"+p,c)},t}(p.Component);v.defaultProps={renderTitleComponent:function(e){var t=String(e.scene.route.title||"");return babelHelpers.jsx(s,{},void 0,t)},renderLeftComponent:function(e){return 0!==e.scene.index&&e.onNavigateBack?babelHelpers.jsx(o,{onPress:e.onNavigateBack}):null},renderRightComponent:function(e){return null},statusBarHeight:0},v.propTypes=babelHelpers.extends({},a.SceneRendererProps,{onNavigateBack:f.func,renderLeftComponent:f.func,renderRightComponent:f.func,renderTitleComponent:f.func,style:b.propTypes.style,statusBarHeight:f.number,viewProps:f.shape(b.propTypes)}),v.HEIGHT=56,v.Title=s,v.BackButton=o;var g=h.create({appbar:{alignItems:"center",backgroundColor:"#FFF",borderBottomColor:"rgba(0, 0, 0, .15)",borderBottomWidth:0,elevation:4,flexDirection:"row",justifyContent:"flex-start"},title:{bottom:0,left:56,position:"absolute",right:56,top:0},left:{bottom:0,left:0,position:"absolute",top:0},right:{bottom:0,position:"absolute",right:0,top:0}});n.exports=v},351);