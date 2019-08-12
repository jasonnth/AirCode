__d(function(e,t,a,i){function r(e){if(!e)return null;var t=e.author_id,a=e.comments,i=e.id,r=e.author_guest_profile,n=e.payout_native,o=e.private_feedback,u=e.rating,l=e.scheduled_template,s=e.template;return{associatedGuestId:t,guestProfileId:r.id,associatedTripInstanceId:l.id,associatedTripTemplateId:s.id,comments:a,id:i,nativePayout:n,privateFeedback:o,rating:u}}function n(e){if(!e)return null;var t=e.payout_native,a=e.payout_provider_name,i=e.payout_success,r=e.scheduled_template_id,n=e.template_id;return{associatedTripInstanceId:r,associatedTripReservationId:e.trip_id,associatedTripTemplateId:n,nativePayout:t,payoutProviderName:a,payoutSuccess:i}}function o(e){var t=arguments.length>1&&void 0!==arguments[1]?arguments[1]:{};if(!e)return null;var a=e.guest,i=e.id,r=e.thread_id,n=t.detailsFetched,o=a.about,u=a.created_at,l=a.first_name;return{about:o,associatedGuestId:a.id,associatedThreadId:r,createdAt:u,detailsFetched:n,firstName:l,guestProfileId:i,location:a.location,phone:a.phone,pictureUrl:a.picture_url,pictureUrlLarge:a.picture_url_large,profilePictureUrl:a.profile_pic_path,work:a.work}}function u(e){if(!e)return null;var t=e.guest_count,a=e.guest_profiles,i=e.id,r=a.map(function(e){return o(e,{detailsFetched:!1})});return{associatedGuestIds:r.map(function(e){return e.associatedGuestId}),guestProfileIds:r.map(function(e){return e.guestProfileId}),guestsCount:t,id:i}}function l(e){return e?{phone:e.phone,timezone:e.time_zone,timezoneOffset:e.time_zone_offset}:null}function s(e){return e?{locale:e.locale,name:e.name}:null}function d(e){var t=arguments.length>1&&void 0!==arguments[1]?arguments[1]:{};if(!e)return null;var a=e.booking_lead_hours,i=e.confirmed_booking_lead_hours,r=e.default_locale,n=e.default_num_days,o=e.descriptions,u=e.id,d=e.market,c=e.max_guests,p=e.pending_poster_pictures,f=e.poster_pictures,_=e.price_currency,m=e.price_per_guest,g=e.product_type,h=e.queue_status,v=e.status,y=e.experiences,I=t.detailsFetched,P=y.map(function(e){return e.id}),b=o.map(function(e){return s(e)});return{associatedExperienceTemplateIds:P,bookingLeadHours:a,confirmedBookingLeadHours:i,defaultDurationDays:n,defaultLocale:r,descriptionsByLocale:(0,T.default)(b,"locale"),detailsFetched:I,id:u,market:d?l(d):null,maxGuests:c,pendingPosterPictures:p,posterPictures:f,priceCurrency:_,pricePerGuest:m,productType:g,queueStatus:h,status:v}}function c(e){var t=arguments.length>1&&void 0!==arguments[1]?arguments[1]:{};if(!e)return null;var a=e.booked_trips,i=e.booking_closes_at,r=e.ends_at,n=e.flags,o=e.id,u=e.num_days,l=e.num_guests,s=e.scheduled_experiences,d=e.start_date,c=e.starts_at,p=e.template_id,f=e.thread_id,_=e.total_payout_native,m=t.detailsFetched;return{associatedExperienceInstanceIds:s.map(function(e){return e.id}),associatedThreadId:f,associatedTripReservationIds:a?a.map(function(e){return e.id}):null,associatedTripTemplateId:p,detailsFetched:m,durationDays:u,endsAt:r,guestsCount:l,flags:n,id:o,startDate:d,startsAt:c,totalNativePayout:_,bookingClosesAt:i}}function p(e){return e?{locale:e.locale,mapHeading:e.map_heading,name:e.name,whatIsNotIncluded:e.what_is_not_included,whatYourGuestsWillDo:e.what_you_will_do,whereYouWillTakeThem:e.where_i_will_take_you}:null}function f(e){if(!e)return null;var t=e.category,a=e.description;return{associatedExperienceTemplateId:e.experience_id,category:t,description:a,id:e.id,locale:e.locale,name:e.name}}function _(e){return e?{directions:e.directions,locale:e.locale,name:e.name}:null}function m(e){var t=arguments.length>1&&void 0!==arguments[1]?arguments[1]:{};if(!e)return null;var a=e.address,i=e.apt,r=e.city,n=e.country,o=e.id,u=e.descriptions,l=e.lat,s=e.lng,d=e.state,c=e.street_address,p=e.zipcode,f=t.defaultLocale,m=l&&Number(l),g=s&&Number(s),h=u.map(function(e){return _(e)});return{address:a,apt:i,city:r,country:n,defaultLocale:f,descriptionsByLocale:(0,T.default)(h,"locale"),id:o,lat:m,lng:g,state:d,street_address:c,zipcode:p}}function g(e){var t=arguments.length>1&&void 0!==arguments[1]?arguments[1]:{};if(!e)return null;var a=e.amenities,i=e.default_locale,r=e.default_minute,n=e.descriptions,o=e.duration_hours,u=e.gallery_pictures,l=e.id,s=e.location,d=e.pending_pictures,c=e.template_id,_=t.detailsFetched,g=a?a.map(function(e){return f(e)}):null,h=g?(0,y.default)(g,"locale"):null,v=s?m(s,{defaultLocale:i}):null,I=n.map(function(e){return p(e)});return{amenityArraysByLocale:h,associatedTripTemplateId:c,galleryPictures:u,defaultLocale:i,defaultMinute:r,descriptionsByLocale:(0,T.default)(I,"locale"),detailsFetched:_,durationHours:o,id:l,location:v,pendingPictures:d}}function h(e){var t=arguments.length>1&&void 0!==arguments[1]?arguments[1]:{};if(!e)return null;var a=e.experience,i=e.experience_id,r=e.id,n=e.location,o=e.scheduled_template_id,u=e.starts_at,l=t.detailsFetched,s=l?a.default_locale:null;return{associatedExperienceTemplateId:i,associatedTripInstanceId:o,detailsFetched:l,id:r,location:l?m(n,{defaultLocale:s}):null,startsAt:u}}Object.defineProperty(i,"__esModule",{value:!0}),i.formatReview=r,i.formatPayout=n,i.formatGuestProfile=o,i.formatTripReservation=u,i.formatMarket=l,i.formatTripTemplateDescription=s,i.formatTripTemplate=d,i.formatTripInstance=c,i.formatExperienceTemplateDescription=p,i.formatExperienceTemplateAmenity=f,i.formatLocationDescription=_,i.formatLocation=m,i.formatExperienceTemplate=g,i.formatExperienceInstance=h;var v=t(1399),y=babelHelpers.interopRequireDefault(v),I=t(678),T=babelHelpers.interopRequireDefault(I)},1773);