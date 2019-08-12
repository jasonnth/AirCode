__d(function(e,t,a,r){Object.defineProperty(r,"__esModule",{value:!0});var n=t(412),i=babelHelpers.interopRequireDefault(n),o=t(271),l=babelHelpers.interopRequireDefault(o),s=t(44),u=t(1509),d=babelHelpers.interopRequireDefault(u),p=t(379),f=babelHelpers.interopRequireDefault(p),c=t(772),b=babelHelpers.interopRequireDefault(c),g=t(1510),h=babelHelpers.interopRequireDefault(g),D=function(e){function t(a){babelHelpers.classCallCheck(this,t);var r=babelHelpers.possibleConstructorReturn(this,e.call(this,a));return r.state={showDatePickerIOS:!1,datePickerIOSDateFieldName:null},r.onDateChangeIOS=r.onDateChangeIOS.bind(r),r.onToggleDatePicker=r.onToggleDatePicker.bind(r),r}return babelHelpers.inherits(t,e),t.prototype.onToggleDatePickerIOS=function(e,t){this.setState({showDatePickerIOS:e,datePickerIOSDateFieldName:t})},t.prototype.onToggleDatePickerAndroid=function(e,t){var a,r,n,i,o,l;return regeneratorRuntime.async(function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,regeneratorRuntime.awrap(s.DatePickerAndroid.open({date:this.props.form[t]}));case 2:a=e.sent,r=a.action,n=a.year,i=a.month,o=a.day,r!==s.DatePickerAndroid.dismissedAction&&(l=new Date(n,i,o),this.props.onChange(t,l));case 8:case"end":return e.stop()}},null,this)},t.prototype.onToggleDatePicker=function(e,t){"ios"===s.Platform.OS?this.onToggleDatePickerIOS(e,t):"android"===s.Platform.OS&&this.onToggleDatePickerAndroid(e,t)},t.prototype.onDateChangeIOS=function(e){var t=this.state.datePickerIOSDateFieldName;t&&this.props.onChange(t,e)},t.prototype.validate=function(){var e=this.props,t=e.validator,a=e.rows,r=e.form;return t(r).isValid&&a.every(function(e){return!e.validator||e.validator(r[e.name]).isValid})},t.prototype.render=function(){var e=this,t=this.props,a=t.title,r=t.subtitle,n=t.rows,i=t.form,o=t.lastScreen,u=t.onPressNext,p=t.toasts,c=t.loading,g=o?f.default.phrase("Submit",null,"Text on a button link to submit in wizzard"):f.default.phrase("Next",null,"Text on a button link to the next step in wizzard");return babelHelpers.jsx(b.default,{title:a,subtitle:r,toasts:p,footer:babelHelpers.jsx(s.View,{},void 0,babelHelpers.jsx(d.default,{babu:!0,loading:c,buttonText:g,buttonDisabled:c||!this.validate(),onButtonPress:u}),this.state.showDatePickerIOS&&babelHelpers.jsx(s.DatePickerIOS,{date:i[this.state.datePickerIOSDateFieldName]||new Date,mode:"date",onDateChange:this.onDateChangeIOS}))},void 0,n.map(function(t){return l.default.createElement(h.default,babelHelpers.extends({key:t.type+"-"+t.name,value:i[t.name],onChange:function(a){return e.props.onChange(t.name,a)},onToggleDatePicker:e.onToggleDatePicker},t))}))},t}(o.PureComponent);D.propTypes={title:i.default.string.isRequired,subtitle:i.default.string,rows:i.default.arrayOf(i.default.shape({type:i.default.string.isRequired,name:i.default.string.isRequired,label:i.default.string,data:i.default.object,validator:i.default.func})).isRequired,lastScreen:i.default.bool,onChange:i.default.func.isRequired,onPressNext:i.default.func.isRequired,form:i.default.object.isRequired,toasts:i.default.node,loading:i.default.bool,validator:i.default.func},D.defaultProps={subtitle:"",toasts:null,loading:!1,lastScreen:!1,validator:function(){return{isValid:!0}}},r.default=D},1508);