__d(function(e,r,t,i){"use strict";function n(e){if(e&&e.__esModule)return e;var r={};if(null!=e)for(var t in e)Object.prototype.hasOwnProperty.call(e,t)&&(r[t]=e[t]);return r.default=e,r}Object.defineProperty(i,"__esModule",{value:!0}),i.ExperiencesBookingFlowClickAgreeRequirementsEvent=void 0;var o=r(412),s=function(e){return e&&e.__esModule?e:{default:e}}(o),u=r(694),a=n(u),l=r(1611),p=n(l),c=r(698),d=n(c);i.ExperiencesBookingFlowClickAgreeRequirementsEvent={defaultProps:{schema:"com.airbnb.jitney.event.logging.ExperiencesBookingFlow:ExperiencesBookingFlowClickAgreeRequirementsEvent:1.0.0",event_name:"experiencesbookingflow_click_agree_requirements",page:"review_conditions",target:"agree",operation:2},propTypes:{schema:s.default.string,event_name:s.default.string.isRequired,context:s.default.shape(a.Context.propTypes).isRequired,page:s.default.string.isRequired,target:s.default.string.isRequired,operation:s.default.oneOf(Object.values(d.Operation)).isRequired,product_info:s.default.shape(p.ProductInfo.propTypes).isRequired}}},1610);