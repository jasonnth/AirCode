__d(function(e,t,r,n){Object.defineProperty(n,"__esModule",{value:!0});var i=t(412),l=babelHelpers.interopRequireDefault(i),o=t(271),u=(babelHelpers.interopRequireDefault(o),t(44)),s=t(790),a=t(654),d=t(759),p=babelHelpers.interopRequireDefault(d),c=t(379),f=babelHelpers.interopRequireDefault(c),b=t(410),h=babelHelpers.interopRequireDefault(b),C=t(1055),y=babelHelpers.interopRequireDefault(C),D=t(2297),g=t(2300),v=t(2313),H=babelHelpers.interopRequireDefault(v),I=t(2314),R=babelHelpers.interopRequireDefault(I),q=t(2316),P=babelHelpers.interopRequireDefault(q),m=t(2311),x=function(e){function t(r){babelHelpers.classCallCheck(this,t);var n=babelHelpers.possibleConstructorReturn(this,e.call(this,r));return n.onNextPressed=n.onNextPressed.bind(n),n}return babelHelpers.inherits(t,e),t.prototype.onNextPressed=function(e){var t=this.props.idCountries,r=t.find(function(t){return t.countryCodeAlpha3===e.value});this.props.idCountryChanged(r),h.default.dismiss()},t.prototype.componentDidMount=function(){var e=this.props,t=e.isFetchingIDCountries;null!=e.idCountries||t||this.props.loadIDCountries()},t.prototype.render=function(){var e=this.props,t=e.idCountries,r=e.selectedIDCountry,n=e.isFetchingIDCountries,i=e.light,l={value:r&&r.countryCodeAlpha3},o=t&&t.map(function(e){return{value:e.countryCodeAlpha3,label:e.name}});if(t){var s=o.find(function(e){return e.value===l.value});o.splice(o.indexOf(s),1),o=[s].concat(babelHelpers.toConsumableArray(o))}return babelHelpers.jsx(u.View,{style:u.StyleSheet.absoluteFill},void 0,babelHelpers.jsx(y.default,{title:f.default.phrase("Which country issued your ID?",null,"Prompt to select a country from the list"),isLoading:n,options:o,selectedOption:l,onNextPress:this.onNextPressed,nextDisabled:!r,includeSearch:!0,searchPlaceholder:f.default.phrase("Type country",null,"Placeholder text in a search field in identity verification flow"),light:i}),babelHelpers.jsx(H.default,{}))},t}(o.Component);x.propTypes={loadIDCountries:l.default.func.isRequired,idCountryChanged:l.default.func.isRequired,isFetchingIDCountries:l.default.bool.isRequired,idCountries:l.default.arrayOf(R.default),selectedIDCountry:R.default,light:l.default.bool},x.defaultProps={light:!1},x.getPageNameForImpressionLogging=function(){return"verification_select_id_country"},n.default=(0,P.default)(m.ID_COUNTRY_SELECTION_PAGE)((0,s.connect)(function(e){return(0,p.default)((0,D.governmentIDSelector)(e),["idCountries","selectedIDCountry","isFetchingIDCountries"])},function(e){return{loadIDCountries:(0,a.bindActionCreators)(g.loadIDCountries,e),idCountryChanged:(0,a.bindActionCreators)(g.idCountryChanged,e)}})(x))},2320);