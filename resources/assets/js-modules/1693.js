__d(function(e,t,r,l){Object.defineProperty(l,"__esModule",{value:!0});var a=t(412),i=babelHelpers.interopRequireDefault(a),p=t(271),u=babelHelpers.interopRequireDefault(p),n=t(379),s=babelHelpers.interopRequireDefault(n),o=t(562),d=babelHelpers.interopRequireDefault(o),f=t(924),b=babelHelpers.interopRequireDefault(f),c=t(410),h=babelHelpers.interopRequireDefault(c),T=t(1694),D=babelHelpers.interopRequireDefault(T),R=t(1609),m=t(1680),H=t(1684),v=t(1695),y=babelHelpers.interopRequireDefault(v),q=t(1698),A=babelHelpers.interopRequireDefault(q),P=t(1634),M=babelHelpers.interopRequireDefault(P),_=t(1569),C=babelHelpers.interopRequireWildcard(_),g=t(1608),w=babelHelpers.interopRequireWildcard(g),I={isPreview:i.default.bool,scheduledTripTemplates:i.default.arrayOf(A.default).isRequired,tripTemplate:M.default.isRequired},S={isPreview:!1},E=function(e){function t(r){babelHelpers.classCallCheck(this,t);var l=babelHelpers.possibleConstructorReturn(this,e.call(this,r)),a=l.props.scheduledTripTemplates;return l.scheduledTripDateManager=new y.default(a),l}return babelHelpers.inherits(t,e),t.prototype.componentWillReceiveProps=function(e){var t=this.props.scheduledTripTemplates,r=e.scheduledTripTemplates;t&&r&&!(0,b.default)(t.map(function(e){return e.id}).sort(),r.map(function(e){return e.id}).sort())&&(this.scheduledTripDateManager=new y.default(r))},t.prototype.render=function(){var e=this,t=this.props,r=t.isPreview,l=t.tripTemplate,a=t.scheduledTripTemplates;if(!a||0===a.length)return null;var i=(0,m.getAvailabilityMonths)(a).map(function(t,a){var i=t.year,p=t.month;return{year:i,month:p,dayStates:e.scheduledTripDateManager.dayStatesForMonth({month:p,year:i,isSmallView:!0}),onMonthPress:function(){r||((0,R.logPDPClickInfoSection)(w.AVAILABILITY_CALENDAR,l),h.default.push(C.DATE_PICKER_CALENDAR,{tripTemplateId:l.id,scrollMonthKey:a>0?p+"-"+i:null}))}}}),p=s.default.phrase("Availability",null,"Section title for viewing trip availability"),u=(0,d.default)().format(H.STD_DATE_FORMAT);return babelHelpers.jsx(D.default,{title:p,monthsData:i,today:u})},t}(u.default.Component);l.default=E,E.propTypes=I,E.defaultProps=S},1693);