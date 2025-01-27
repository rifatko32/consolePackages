CREATE TYPE operation_type AS ENUM('load', 'unload');

CREATE TABLE public.billing_order
(
    client_id      varchar        NOT NULL,
    order_date     date           NOT NULL,
    amount         decimal        NOT NULL,
    package_qty    int4           NOT NULL,
    truck_id       uuid           NOT NULL,
    "comment"      varchar        NULL,
    operation_type operation_type NOT NULL
);
COMMENT ON TABLE public.billing_order IS 'Счет по биллингу';