__d(function(e,t,l,s){Object.defineProperty(s,"__esModule",{value:!0});var a=t(271),u=babelHelpers.interopRequireDefault(a),r=t(44),o=t(379),n=babelHelpers.interopRequireDefault(o),i=t(773),p=babelHelpers.interopRequireDefault(i),b=t(1050),f=babelHelpers.interopRequireDefault(b),h=t(1033),d=babelHelpers.interopRequireDefault(h),c=t(1057),H=babelHelpers.interopRequireDefault(c),g={},m=function(e){function t(l){babelHelpers.classCallCheck(this,t);var s=babelHelpers.possibleConstructorReturn(this,e.call(this,l));return s.state={messageToHost:"",guests:1},s}return babelHelpers.inherits(t,e),t.prototype.render=function(){var e=this,t=n.default.phrase("Your Message",null,"title for host contact screen"),l=n.default.phrase("%{smart_count} Guest |||| %{smart_count} Guests",{smart_count:this.state.guests},"Label for an input to edit number of guests"),s=void 0;return s=this.state.messageToHost?n.default.phrase("Edit",null,"button text"):n.default.phrase("Add",null,"button text"),babelHelpers.jsx(r.View,{style:r.StyleSheet.absoluteFill},void 0,babelHelpers.jsx(p.default,{title:"Contact Host"}),babelHelpers.jsx(f.default,{name:t,label:"",description:n.default.phrase("Message",null,"something you will write to the host"),sheetTitle:t,value:this.state.messageToHost,valueLabel:s,onChangeText:function(t){return e.setState({messageToHost:t})}}),babelHelpers.jsx(d.default,{name:n.default.phrase("Guests",null,"people who travel"),label:n.default.phrase("Guests",null,"people who travel"),sheetTitle:n.default.phrase("Number of guests",null,"title for num-guests sheet"),value:this.state.guests,minValue:1,maxValue:16,valueLabel:l,onChange:function(t){return e.setState({guests:t})}}),babelHelpers.jsx(H.default,{},void 0,n.default.phrase("Continue",null,"Button text")))},t}(u.default.Component);s.default=m,m.propTypes=g},1722);