__d(function(e,t,a,n){function s(e,t){var a=void 0,n=void 0;return e?(a=(0,u.default)(e,"YYYY-MM-DD").format(p.default.format("ss")),n=t?(0,u.default)(t,"YYYY-MM-DD").format(p.default.format("ss")):"???",a+" - "+n):h.default.phrase("Check In/Out",null,"Empty Label for check in/out date range on a trip")}Object.defineProperty(n,"__esModule",{value:!0});var l=t(412),r=babelHelpers.interopRequireDefault(l),i=t(271),o=(babelHelpers.interopRequireDefault(i),t(44)),d=t(562),u=babelHelpers.interopRequireDefault(d),b=t(1372),p=babelHelpers.interopRequireDefault(b),f=t(379),h=babelHelpers.interopRequireDefault(f),c=t(743),D=t(779),v=babelHelpers.interopRequireDefault(D),g=t(890),S=babelHelpers.interopRequireDefault(g),R=t(422),m=babelHelpers.interopRequireDefault(R),y=t(1373),H=babelHelpers.interopRequireDefault(y),x=t(1382),C=babelHelpers.interopRequireDefault(x),P=t(1043),j=babelHelpers.interopRequireDefault(P),q=t(410),V=babelHelpers.interopRequireDefault(q),k=t(1381),Y=babelHelpers.interopRequireDefault(k),M=t(750),w=babelHelpers.interopRequireDefault(M),I=(0,c.forbidExtraProps)({minDate:r.default.string,maxDate:r.default.string,minSelectableDate:Y.default,startDate:Y.default,endDate:Y.default,onChange:r.default.func,fetchStates:r.default.func.isRequired,name:r.default.string.isRequired,subtitle:r.default.string,valueLabel:r.default.node,onSheetPresented:r.default.func,onSheetClosed:r.default.func,saveButtonLabel:r.default.string,divider:w.default}),T={divider:null,saveButtonLabel:"Save",onSheetClosed:function(){}},L=function(e){function t(a,n){babelHelpers.classCallCheck(this,t);var s=babelHelpers.possibleConstructorReturn(this,e.call(this,a,n));return s.state={sheetVisible:!1,delayRendering:!0,startDate:a.startDate,endDate:a.endDate},s.onModalPresented=s.onModalPresented.bind(s),s.onVisibleChange=s.onVisibleChange.bind(s),s.onSave=s.onSave.bind(s),s.onReset=s.onReset.bind(s),s.onSheetClosed=s.onSheetClosed.bind(s),s.onDateSelected=s.onDateSelected.bind(s),s}return babelHelpers.inherits(t,e),t.prototype.componentWillReceiveProps=function(e){e.startDate===this.props.startDate&&e.endDate===this.props.endDate||this.setState({startDate:e.startDate,endDate:e.endDate})},t.prototype.onModalPresented=function(){this.setState({delayRendering:!1})},t.prototype.onVisibleChange=function(e){var t=this;this.setState({sheetVisible:e,delayRendering:!e||this.state.delayRendering},function(){e||t.onSheetClosed()})},t.prototype.onSave=function(){this.setState({sheetVisible:!1}),(this.state.startDate!==this.props.startDate||this.state.endDate!==this.props.endDate)&&this.state.startDate&&this.state.endDate&&this.props.onChange(this.state.startDate,this.state.endDate)},t.prototype.onSheetClosed=function(){this.onReset(),this.props.onSheetClosed()},t.prototype.onReset=function(){this.setState({startDate:this.props.startDate,endDate:this.props.endDate})},t.prototype.onDateSelected=function(e,t){this.setState({startDate:e,endDate:t})},t.prototype.renderSheet=function(){var e=this,t=this.props,a=t.minDate,n=t.maxDate,s=t.minSelectableDate,l=t.fetchStates,r=t.saveButtonLabel,i=this.state,d=i.startDate,b=i.endDate,f=m.default.theme,c=f.color,D=f.size,v=h.default.phrase("Check \nIn",null,"Check In date for Date Range Picker"),g=h.default.phrase("Check \nOut",null,"Check Out date for Date Range Picker"),R=function(e){var t=(0,u.default)(e,"YYYY-MM-DD").format(p.default.format("sss")),a=t.lastIndexOf(",");return-1===a&&(a=t.lastIndexOf(" ")),-1===a?t:t.slice(0,a)+"\n"+t.slice(a+1)};return d&&(v=R(d)),b&&(g=R(b)),babelHelpers.jsx(S.default,{visible:this.state.sheetVisible,dark:!1,onModalPresented:this.onModalPresented,backgroundColor:c.white,onVisibleChange:this.onVisibleChange,rightTitle:h.default.phrase("Reset",null,"Reset changes in Date Range Picker"),onRightPress:this.onReset},void 0,babelHelpers.jsx(o.View,{style:[o.StyleSheet.absoluteFill,{paddingTop:D.vertical.navigationBar}]},void 0,babelHelpers.jsx(V.default.Screen,{barType:V.default.Screen.BAR_TYPE.BASIC,link:h.default.phrase("Clear",null,"[DLS Component Library: Navigation Bar Trailing Button] Accessibility label for a clear button"),onLinkPress:function(){return e.onDateSelected(null,null)}}),babelHelpers.jsx(C.default,{start:v,end:g,startEnabled:null!=d,endEnabled:null!=b,divider:"none"}),babelHelpers.jsx(H.default,{minDate:a,maxDate:n,minSelectableDate:s,startDate:d,endDate:b,onDateSelected:this.onDateSelected,fetchStates:l,delayRendering:this.state.delayRendering}),babelHelpers.jsx(o.View,{style:B.button},void 0,babelHelpers.jsx(j.default,{dark:!0,rightIcon:"chevron-right",onPress:this.onSave},void 0,r))))},t.prototype.render=function(){var e=this,t=this.props,a=t.name,n=t.subtitle,l=t.valueLabel,r=t.divider,i=this.state,d=i.startDate,u=i.endDate;return babelHelpers.jsx(v.default,{divider:r,onPress:function(){return e.setState({sheetVisible:!0})}},void 0,babelHelpers.jsx(o.View,{style:B.row},void 0,babelHelpers.jsx(o.View,{},void 0,babelHelpers.jsx(o.Text,{style:B.name},void 0,a),!!n&&babelHelpers.jsx(o.Text,{style:B.subtitle},void 0,n)),babelHelpers.jsx(o.Text,{style:B.input},void 0,void 0===l?s(d,u):l)),this.renderSheet())},t}(i.PureComponent);n.default=L,L.defaultProps=T,L.propTypes=I;var B=m.default.create(function(e){var t=e.bp,a=e.color,n=e.font,s=e.size;return{row:{flexDirection:"row",justifyContent:"space-between",alignItems:"flex-start"},name:babelHelpers.extends({},n.large,{marginVertical:s.vertical.tiny}),subtitle:babelHelpers.extends({},n.small,{marginVertical:s.vertical.tiny}),input:babelHelpers.extends({},n.large,{flex:1,height:32,textAlign:"right",color:a.core.babu,marginTop:s.vertical.tiny}),button:{position:"absolute",bottom:s.vertical.medium,right:4*t}}});L.category="content/specialty",L.examples=[{title:"DateRangeInputRow",component:function(){return babelHelpers.jsx(L,{name:"Date Range Input",minDate:"2016-01-01",maxDate:"2018-01-01",startDate:"2017-08-25",endDate:"2017-08-27",fetchStates:function(){return{calendar_months:[]}}})}}]},1554);