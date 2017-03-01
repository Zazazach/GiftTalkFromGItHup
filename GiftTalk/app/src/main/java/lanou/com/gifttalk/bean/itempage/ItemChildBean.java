package lanou.com.gifttalk.bean.itempage;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by dllo on 17/2/16.
 */

public class ItemChildBean implements Parcelable{




    private int code;
    private DataBean data;
    private String message;

    protected ItemChildBean(Parcel in) {
        code = in.readInt();
        data = in.readParcelable(DataBean.class.getClassLoader());
        message = in.readString();
    }

    public static final Creator<ItemChildBean> CREATOR = new Creator<ItemChildBean>() {
        @Override
        public ItemChildBean createFromParcel(Parcel in) {
            return new ItemChildBean(in);
        }

        @Override
        public ItemChildBean[] newArray(int size) {
            return new ItemChildBean[size];
        }
    };

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(code);
        dest.writeParcelable(data, flags);
        dest.writeString(message);
    }

    public static class DataBean  implements Parcelable{


        private String cover_image;
        private String cover_url;
        private String cover_webp;
        private PagingBean paging;
        private String share_url;
        private List<ItemsBean> items;

        protected DataBean(Parcel in) {
            cover_image = in.readString();
            cover_url = in.readString();
            cover_webp = in.readString();
            paging = in.readParcelable(PagingBean.class.getClassLoader());
            share_url = in.readString();
            items = in.createTypedArrayList(ItemsBean.CREATOR);
        }

        public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel in) {
                return new DataBean(in);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };

        public String getCover_image() {
            return cover_image;
        }

        public void setCover_image(String cover_image) {
            this.cover_image = cover_image;
        }

        public String getCover_url() {
            return cover_url;
        }

        public void setCover_url(String cover_url) {
            this.cover_url = cover_url;
        }

        public String getCover_webp() {
            return cover_webp;
        }

        public void setCover_webp(String cover_webp) {
            this.cover_webp = cover_webp;
        }

        public PagingBean getPaging() {
            return paging;
        }

        public void setPaging(PagingBean paging) {
            this.paging = paging;
        }

        public String getShare_url() {
            return share_url;
        }

        public void setShare_url(String share_url) {
            this.share_url = share_url;
        }

        public List<ItemsBean> getItems() {
            return items;
        }

        public void setItems(List<ItemsBean> items) {
            this.items = items;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(cover_image);
            dest.writeString(cover_url);
            dest.writeString(cover_webp);
            dest.writeParcelable(paging, flags);
            dest.writeString(share_url);
            dest.writeTypedList(items);
        }

        public static class PagingBean implements Parcelable {
            /**
             * next_url : http://api.liwushuo.com/v2/ranks_v3/ranks/1?limit=20&offset=20
             */

            private String next_url;

            protected PagingBean(Parcel in) {
                next_url = in.readString();
            }

            public static final Creator<PagingBean> CREATOR = new Creator<PagingBean>() {
                @Override
                public PagingBean createFromParcel(Parcel in) {
                    return new PagingBean(in);
                }

                @Override
                public PagingBean[] newArray(int size) {
                    return new PagingBean[size];
                }
            };

            public String getNext_url() {
                return next_url;
            }

            public void setNext_url(String next_url) {
                this.next_url = next_url;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(next_url);
            }
        }

        public static class ItemsBean implements Parcelable {


            private int activity_ended_at;
            private int activity_started_at;
            private String cover_image_key;
            private String cover_image_url;
            private String cover_webp_url;
            private int created_at;
            private String description;
            private String detail_html;
            private String feature;
            private int hot_sale_threshold;
            private String id;
            private Object in_merchant_order;
            private int is_haitao;
            private boolean is_puyin;
            private int merchant_id;
            private int merchant_type;
            private String name;
            private String postage;
            private int quota;
            private boolean recommendable;
            private int scarcity_threshold;
            private String short_description;
            private int show_stock;
            private int start_sold_at;
            private int status;
            private boolean support_generic_coupons;
            private int take_down_at;
            private String target_type;
            private String target_url;
            private int total_sold;
            private int updated_at;
            private Object ad_monitors;
            private Object brand_id;
            private Object brand_order;
            private Object category_id;
            private int editor_id;
            private int favorites_count;
            private String keywords;
            private String price;
            private String purchase_id;
            private String purchase_shop_id;
            private int purchase_status;
            private int purchase_type;
            private String purchase_url;
            private int shop_item_id;
            private Object subcategory_id;
            private String url;
            private List<String> image_urls;
            private List<SkusBean> skus;
            private List<SpecsDomainsBean> specs_domains;
            private List<?> post_ids;

            protected ItemsBean(Parcel in) {
                activity_ended_at = in.readInt();
                activity_started_at = in.readInt();
                cover_image_key = in.readString();
                cover_image_url = in.readString();
                cover_webp_url = in.readString();
                created_at = in.readInt();
                description = in.readString();
                detail_html = in.readString();
                feature = in.readString();
                hot_sale_threshold = in.readInt();
                id = in.readString();
                is_haitao = in.readInt();
                is_puyin = in.readByte() != 0;
                merchant_id = in.readInt();
                merchant_type = in.readInt();
                name = in.readString();
                postage = in.readString();
                quota = in.readInt();
                recommendable = in.readByte() != 0;
                scarcity_threshold = in.readInt();
                short_description = in.readString();
                show_stock = in.readInt();
                start_sold_at = in.readInt();
                status = in.readInt();
                support_generic_coupons = in.readByte() != 0;
                take_down_at = in.readInt();
                target_type = in.readString();
                target_url = in.readString();
                total_sold = in.readInt();
                updated_at = in.readInt();
                editor_id = in.readInt();
                favorites_count = in.readInt();
                keywords = in.readString();
                price = in.readString();
                purchase_id = in.readString();
                purchase_shop_id = in.readString();
                purchase_status = in.readInt();
                purchase_type = in.readInt();
                purchase_url = in.readString();
                shop_item_id = in.readInt();
                url = in.readString();
                image_urls = in.createStringArrayList();
                skus = in.createTypedArrayList(SkusBean.CREATOR);
                specs_domains = in.createTypedArrayList(SpecsDomainsBean.CREATOR);
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(activity_ended_at);
                dest.writeInt(activity_started_at);
                dest.writeString(cover_image_key);
                dest.writeString(cover_image_url);
                dest.writeString(cover_webp_url);
                dest.writeInt(created_at);
                dest.writeString(description);
                dest.writeString(detail_html);
                dest.writeString(feature);
                dest.writeInt(hot_sale_threshold);
                dest.writeString(id);
                dest.writeInt(is_haitao);
                dest.writeByte((byte) (is_puyin ? 1 : 0));
                dest.writeInt(merchant_id);
                dest.writeInt(merchant_type);
                dest.writeString(name);
                dest.writeString(postage);
                dest.writeInt(quota);
                dest.writeByte((byte) (recommendable ? 1 : 0));
                dest.writeInt(scarcity_threshold);
                dest.writeString(short_description);
                dest.writeInt(show_stock);
                dest.writeInt(start_sold_at);
                dest.writeInt(status);
                dest.writeByte((byte) (support_generic_coupons ? 1 : 0));
                dest.writeInt(take_down_at);
                dest.writeString(target_type);
                dest.writeString(target_url);
                dest.writeInt(total_sold);
                dest.writeInt(updated_at);
                dest.writeInt(editor_id);
                dest.writeInt(favorites_count);
                dest.writeString(keywords);
                dest.writeString(price);
                dest.writeString(purchase_id);
                dest.writeString(purchase_shop_id);
                dest.writeInt(purchase_status);
                dest.writeInt(purchase_type);
                dest.writeString(purchase_url);
                dest.writeInt(shop_item_id);
                dest.writeString(url);
                dest.writeStringList(image_urls);
                dest.writeTypedList(skus);
                dest.writeTypedList(specs_domains);
            }

            @Override
            public int describeContents() {
                return 0;
            }

            public static final Creator<ItemsBean> CREATOR = new Creator<ItemsBean>() {
                @Override
                public ItemsBean createFromParcel(Parcel in) {
                    return new ItemsBean(in);
                }

                @Override
                public ItemsBean[] newArray(int size) {
                    return new ItemsBean[size];
                }
            };

            public int getActivity_ended_at() {
                return activity_ended_at;
            }

            public void setActivity_ended_at(int activity_ended_at) {
                this.activity_ended_at = activity_ended_at;
            }

            public int getActivity_started_at() {
                return activity_started_at;
            }

            public void setActivity_started_at(int activity_started_at) {
                this.activity_started_at = activity_started_at;
            }

            public String getCover_image_key() {
                return cover_image_key;
            }

            public void setCover_image_key(String cover_image_key) {
                this.cover_image_key = cover_image_key;
            }

            public String getCover_image_url() {
                return cover_image_url;
            }

            public void setCover_image_url(String cover_image_url) {
                this.cover_image_url = cover_image_url;
            }

            public String getCover_webp_url() {
                return cover_webp_url;
            }

            public void setCover_webp_url(String cover_webp_url) {
                this.cover_webp_url = cover_webp_url;
            }

            public int getCreated_at() {
                return created_at;
            }

            public void setCreated_at(int created_at) {
                this.created_at = created_at;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getDetail_html() {
                return detail_html;
            }

            public void setDetail_html(String detail_html) {
                this.detail_html = detail_html;
            }

            public String getFeature() {
                return feature;
            }

            public void setFeature(String feature) {
                this.feature = feature;
            }

            public int getHot_sale_threshold() {
                return hot_sale_threshold;
            }

            public void setHot_sale_threshold(int hot_sale_threshold) {
                this.hot_sale_threshold = hot_sale_threshold;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public Object getIn_merchant_order() {
                return in_merchant_order;
            }

            public void setIn_merchant_order(Object in_merchant_order) {
                this.in_merchant_order = in_merchant_order;
            }

            public int getIs_haitao() {
                return is_haitao;
            }

            public void setIs_haitao(int is_haitao) {
                this.is_haitao = is_haitao;
            }

            public boolean isIs_puyin() {
                return is_puyin;
            }

            public void setIs_puyin(boolean is_puyin) {
                this.is_puyin = is_puyin;
            }

            public int getMerchant_id() {
                return merchant_id;
            }

            public void setMerchant_id(int merchant_id) {
                this.merchant_id = merchant_id;
            }

            public int getMerchant_type() {
                return merchant_type;
            }

            public void setMerchant_type(int merchant_type) {
                this.merchant_type = merchant_type;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPostage() {
                return postage;
            }

            public void setPostage(String postage) {
                this.postage = postage;
            }

            public int getQuota() {
                return quota;
            }

            public void setQuota(int quota) {
                this.quota = quota;
            }

            public boolean isRecommendable() {
                return recommendable;
            }

            public void setRecommendable(boolean recommendable) {
                this.recommendable = recommendable;
            }

            public int getScarcity_threshold() {
                return scarcity_threshold;
            }

            public void setScarcity_threshold(int scarcity_threshold) {
                this.scarcity_threshold = scarcity_threshold;
            }

            public String getShort_description() {
                return short_description;
            }

            public void setShort_description(String short_description) {
                this.short_description = short_description;
            }

            public int getShow_stock() {
                return show_stock;
            }

            public void setShow_stock(int show_stock) {
                this.show_stock = show_stock;
            }

            public int getStart_sold_at() {
                return start_sold_at;
            }

            public void setStart_sold_at(int start_sold_at) {
                this.start_sold_at = start_sold_at;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public boolean isSupport_generic_coupons() {
                return support_generic_coupons;
            }

            public void setSupport_generic_coupons(boolean support_generic_coupons) {
                this.support_generic_coupons = support_generic_coupons;
            }

            public int getTake_down_at() {
                return take_down_at;
            }

            public void setTake_down_at(int take_down_at) {
                this.take_down_at = take_down_at;
            }

            public String getTarget_type() {
                return target_type;
            }

            public void setTarget_type(String target_type) {
                this.target_type = target_type;
            }

            public String getTarget_url() {
                return target_url;
            }

            public void setTarget_url(String target_url) {
                this.target_url = target_url;
            }

            public int getTotal_sold() {
                return total_sold;
            }

            public void setTotal_sold(int total_sold) {
                this.total_sold = total_sold;
            }

            public int getUpdated_at() {
                return updated_at;
            }

            public void setUpdated_at(int updated_at) {
                this.updated_at = updated_at;
            }

            public Object getAd_monitors() {
                return ad_monitors;
            }

            public void setAd_monitors(Object ad_monitors) {
                this.ad_monitors = ad_monitors;
            }

            public Object getBrand_id() {
                return brand_id;
            }

            public void setBrand_id(Object brand_id) {
                this.brand_id = brand_id;
            }

            public Object getBrand_order() {
                return brand_order;
            }

            public void setBrand_order(Object brand_order) {
                this.brand_order = brand_order;
            }

            public Object getCategory_id() {
                return category_id;
            }

            public void setCategory_id(Object category_id) {
                this.category_id = category_id;
            }

            public int getEditor_id() {
                return editor_id;
            }

            public void setEditor_id(int editor_id) {
                this.editor_id = editor_id;
            }

            public int getFavorites_count() {
                return favorites_count;
            }

            public void setFavorites_count(int favorites_count) {
                this.favorites_count = favorites_count;
            }

            public String getKeywords() {
                return keywords;
            }

            public void setKeywords(String keywords) {
                this.keywords = keywords;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getPurchase_id() {
                return purchase_id;
            }

            public void setPurchase_id(String purchase_id) {
                this.purchase_id = purchase_id;
            }

            public String getPurchase_shop_id() {
                return purchase_shop_id;
            }

            public void setPurchase_shop_id(String purchase_shop_id) {
                this.purchase_shop_id = purchase_shop_id;
            }

            public int getPurchase_status() {
                return purchase_status;
            }

            public void setPurchase_status(int purchase_status) {
                this.purchase_status = purchase_status;
            }

            public int getPurchase_type() {
                return purchase_type;
            }

            public void setPurchase_type(int purchase_type) {
                this.purchase_type = purchase_type;
            }

            public String getPurchase_url() {
                return purchase_url;
            }

            public void setPurchase_url(String purchase_url) {
                this.purchase_url = purchase_url;
            }

            public int getShop_item_id() {
                return shop_item_id;
            }

            public void setShop_item_id(int shop_item_id) {
                this.shop_item_id = shop_item_id;
            }

            public Object getSubcategory_id() {
                return subcategory_id;
            }

            public void setSubcategory_id(Object subcategory_id) {
                this.subcategory_id = subcategory_id;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public List<String> getImage_urls() {
                return image_urls;
            }

            public void setImage_urls(List<String> image_urls) {
                this.image_urls = image_urls;
            }

            public List<SkusBean> getSkus() {
                return skus;
            }

            public void setSkus(List<SkusBean> skus) {
                this.skus = skus;
            }

            public List<SpecsDomainsBean> getSpecs_domains() {
                return specs_domains;
            }

            public void setSpecs_domains(List<SpecsDomainsBean> specs_domains) {
                this.specs_domains = specs_domains;
            }

            public List<?> getPost_ids() {
                return post_ids;
            }

            public void setPost_ids(List<?> post_ids) {
                this.post_ids = post_ids;
            }

            public static class SkusBean  implements  Parcelable{


                private Object cover_image_url;
                private String fixed_price;
                private int id;
                private int item_id;
                private String price;
                private int sold;
                private int stock;
                private String supply_price;
                private List<SpecsBean> specs;

                protected SkusBean(Parcel in) {
                    fixed_price = in.readString();
                    id = in.readInt();
                    item_id = in.readInt();
                    price = in.readString();
                    sold = in.readInt();
                    stock = in.readInt();
                    supply_price = in.readString();
                }

                public static final Creator<SkusBean> CREATOR = new Creator<SkusBean>() {
                    @Override
                    public SkusBean createFromParcel(Parcel in) {
                        return new SkusBean(in);
                    }

                    @Override
                    public SkusBean[] newArray(int size) {
                        return new SkusBean[size];
                    }
                };

                public Object getCover_image_url() {
                    return cover_image_url;
                }

                public void setCover_image_url(Object cover_image_url) {
                    this.cover_image_url = cover_image_url;
                }

                public String getFixed_price() {
                    return fixed_price;
                }

                public void setFixed_price(String fixed_price) {
                    this.fixed_price = fixed_price;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getItem_id() {
                    return item_id;
                }

                public void setItem_id(int item_id) {
                    this.item_id = item_id;
                }

                public String getPrice() {
                    return price;
                }

                public void setPrice(String price) {
                    this.price = price;
                }

                public int getSold() {
                    return sold;
                }

                public void setSold(int sold) {
                    this.sold = sold;
                }

                public int getStock() {
                    return stock;
                }

                public void setStock(int stock) {
                    this.stock = stock;
                }

                public String getSupply_price() {
                    return supply_price;
                }

                public void setSupply_price(String supply_price) {
                    this.supply_price = supply_price;
                }

                public List<SpecsBean> getSpecs() {
                    return specs;
                }

                public void setSpecs(List<SpecsBean> specs) {
                    this.specs = specs;
                }

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(fixed_price);
                    dest.writeInt(id);
                    dest.writeInt(item_id);
                    dest.writeString(price);
                    dest.writeInt(sold);
                    dest.writeInt(stock);
                    dest.writeString(supply_price);
                }

                public static class SpecsBean {
                    /**
                     * property : 均码
                     * title : 规格
                     */

                    private String property;
                    private String title;

                    public String getProperty() {
                        return property;
                    }

                    public void setProperty(String property) {
                        this.property = property;
                    }

                    public String getTitle() {
                        return title;
                    }

                    public void setTitle(String title) {
                        this.title = title;
                    }
                }
            }

            public static class SpecsDomainsBean implements Parcelable {
                /**
                 * domains : ["均码"]
                 * title : 规格
                 */

                private String title;
                private List<String> domains;

                protected SpecsDomainsBean(Parcel in) {
                    title = in.readString();
                    domains = in.createStringArrayList();
                }

                public static final Creator<SpecsDomainsBean> CREATOR = new Creator<SpecsDomainsBean>() {
                    @Override
                    public SpecsDomainsBean createFromParcel(Parcel in) {
                        return new SpecsDomainsBean(in);
                    }

                    @Override
                    public SpecsDomainsBean[] newArray(int size) {
                        return new SpecsDomainsBean[size];
                    }
                };

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public List<String> getDomains() {
                    return domains;
                }

                public void setDomains(List<String> domains) {
                    this.domains = domains;
                }

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(title);
                    dest.writeStringList(domains);
                }
            }
        }
    }
}
