__d(function(e,t,n,a){Object.defineProperty(a,"__esModule",{value:!0});var o=t(412),l=babelHelpers.interopRequireDefault(o),i=t(271),p=(babelHelpers.interopRequireDefault(i),t(44)),r=t(422),s=babelHelpers.interopRequireDefault(r),d=t(815),u=babelHelpers.interopRequireDefault(d),c={expanded:l.default.bool,collapsedContent:l.default.node,children:l.default.node},b={children:null,collapsedContent:null,expanded:!1},h=function(e){function t(n){babelHelpers.classCallCheck(this,t);var a=babelHelpers.possibleConstructorReturn(this,e.call(this,n));return a.state={expanded:n.expanded},a}return babelHelpers.inherits(t,e),t.prototype.componentWillMount=function(){this.setState({expanded:this.props.expanded})},t.prototype.componentWillReceiveProps=function(e){e.expanded!==this.props.expanded&&this.setState({expanded:e.expanded})},t.prototype.componentWillUpdate=function(e,t){t.expanded!==this.state.expanded&&p.LayoutAnimation.configureNext(p.LayoutAnimation.Presets.easeInEaseOut)},t.prototype.setExpand=function(e){this.state.expanded!==e&&this.setState({expanded:e})},t.prototype.render=function(){var e=this,t=this.props,n=t.children,a=t.collapsedContent,o=this.state.expanded;return babelHelpers.jsx(p.TouchableWithoutFeedback,{onPress:function(){return e.setExpand(!o)}},void 0,babelHelpers.jsx(p.View,{},void 0,babelHelpers.jsx(p.View,{style:x.collapsedContentRow},void 0,a,babelHelpers.jsx(p.View,{style:x.expandableIndicator},void 0,babelHelpers.jsx(u.default,{name:o?"chevron-up":"chevron-down",size:14}))),this.state.expanded&&n))},t}(i.PureComponent);a.default=h;var x=s.default.create(function(e){var t=e.size;return{collapsedContentRow:{flexDirection:"row",justifyContent:"space-between"},expandableIndicator:{marginTop:t.vertical.tiny,paddingRight:t.horizontal.tiny,marginRight:-t.horizontal.tiny}}});h.defaultProps=b,h.propTypes=c},1734);