__d(function(e,i,t,n){"use strict";function r(e){if(e&&e.__esModule)return e;var i={};if(null!=e)for(var t in e)Object.prototype.hasOwnProperty.call(e,t)&&(i[t]=e[t]);return i.default=e,i}Object.defineProperty(n,"__esModule",{value:!0}),n.GiftingFlowSelectGiftingDesignEvent=void 0;var g=i(412),o=function(e){return e&&e.__esModule?e:{default:e}}(g),s=i(694),f=r(s),a=i(698),l=r(a);n.GiftingFlowSelectGiftingDesignEvent={defaultProps:{schema:"com.airbnb.jitney.event.logging.GiftingFlow:GiftingFlowSelectGiftingDesignEvent:1.0.0",event_name:"giftingflow_select_gifting_design",page:"gifting_creation",section:"gifting_design",operation:2},propTypes:{schema:o.default.string,event_name:o.default.string.isRequired,context:o.default.shape(f.Context.propTypes).isRequired,page:o.default.string.isRequired,section:o.default.string.isRequired,operation:o.default.oneOf(Object.values(l.Operation)).isRequired,gifting_design:o.default.string.isRequired}}},1898);