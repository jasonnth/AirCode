__d(function(e,t,n,o){Object.defineProperty(o,"__esModule",{value:!0});var i=t(271),s=babelHelpers.interopRequireDefault(i),r=t(834),l=t(44),c=t(782),p=babelHelpers.interopRequireDefault(c),a=t(422),u=babelHelpers.interopRequireDefault(a),d={sectionTitles:r.Types.array.isRequired,onSectionSelect:r.Types.func,setTimeout:r.Types.func.isRequired},f={onSectionSelect:function(){}},S=function(e){function t(n,o){babelHelpers.classCallCheck(this,t);var i=babelHelpers.possibleConstructorReturn(this,e.call(this,n,o));return i.lastSelectedIndex=null,i.sectionTitleRefs={},i.topIndexMeasure={},i.onSectionSelect=i.onSectionSelect.bind(i),i.resetSection=i.resetSection.bind(i),i.detectAndScrollToSection=i.detectAndScrollToSection.bind(i),i.setSectionTitleRef=i.setSectionTitleRef.bind(i),i}return babelHelpers.inherits(t,e),t.prototype.componentDidMount=function(){var e=this;if(this.props.sectionTitles.length>0){var t=this.sectionTitleRefs[this.props.sectionTitles[0]];this.props.setTimeout(function(){return t.measure(function(t,n,o,i,s,r){e.topIndexMeasure={y:r,height:i}})},1e3)}},t.prototype.onSectionSelect=function(e){this.props.onSectionSelect(e)},t.prototype.setSectionTitleRef=function(e,t){this.sectionTitleRefs[e]=t},t.prototype.detectAndScrollToSection=function(e){var t=e.nativeEvent.touches[0].pageY,n=this.topIndexMeasure,o=n.y,i=n.height;if(o&&i&&!(t<o)){var s=Math.min(Math.floor((t-o)/i),this.props.sectionTitles.length-1);this.lastSelectedIndex!==s&&(this.lastSelectedIndex=s,this.onSectionSelect(this.props.sectionTitles[s],!0))}},t.prototype.resetSection=function(){this.lastSelectedIndex=null},t.prototype.render=function(){var e=this,t=this.props.sectionTitles;return babelHelpers.jsx(l.View,{style:h.container,onStartShouldSetResponder:function(){return!0},onMoveShouldSetResponder:function(){return!0},onResponderGrant:this.detectAndScrollToSection,onResponderMove:this.detectAndScrollToSection,onResponderRelease:this.resetSection},void 0,t.map(function(t){return s.default.createElement(l.View,{key:t,pointerEvents:"none",ref:e.setSectionTitleRef.bind(e,t)},babelHelpers.jsx(l.Text,{style:h.sectionTitle},void 0,t))}))},t}(s.default.Component);S.defaultProps=f,S.propTypes=d;var h=u.default.create(function(e){var t=e.font;return{container:{alignItems:"center",backgroundColor:"transparent",bottom:0,justifyContent:"center",position:"absolute",right:5,top:0,paddingLeft:6*e.bp},sectionTitle:t.small}});o.default=(0,p.default)(S)},1745);