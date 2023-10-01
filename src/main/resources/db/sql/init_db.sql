

CREATE TABLE categories (
                                   id bigint NOT NULL,
                                   name character varying(255) NOT NULL
);


ALTER TABLE categories OWNER TO postgres;

--
-- Name: categories_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE categories_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE categories_id_seq OWNER TO postgres;

--
-- Name: categories_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE categories_id_seq OWNED BY categories.id;


--
-- Name: comments; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE comments (
                                 id bigint NOT NULL,
                                 created_at timestamp without time zone NOT NULL,
                                 text character varying(255) NOT NULL,
                                 author_id bigint NOT NULL,
                                 petitions_id bigint NOT NULL
);


ALTER TABLE comments OWNER TO postgres;

--
-- Name: comments_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE comments_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE comments_id_seq OWNER TO postgres;

--
-- Name: comments_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE comments_id_seq OWNED BY comments.id;


--
-- Name: files; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE files (
                              id bigint NOT NULL,
                              file_name character varying(255) NOT NULL,
                              location_path character varying(255) NOT NULL
);


ALTER TABLE files OWNER TO postgres;

--
-- Name: files_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE files_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE files_id_seq OWNER TO postgres;

--
-- Name: files_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE files_id_seq OWNED BY files.id;


--
-- Name: permissions; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE permissions (
                                    name character varying(255) NOT NULL,
                                    description character varying(255) NOT NULL
);


ALTER TABLE permissions OWNER TO postgres;

--
-- Name: petitions; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE petitions (
                                  id bigint NOT NULL,
                                  body text NOT NULL,
                                  created_at timestamp without time zone NOT NULL,
                                  title character varying(255) NOT NULL,
                                  author_id bigint NOT NULL,
                                  category_id bigint NOT NULL,
                                  photo_id bigint
);


ALTER TABLE petitions OWNER TO postgres;

--
-- Name: petitions_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE petitions_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE petitions_id_seq OWNER TO postgres;

--
-- Name: petitions_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE petitions_id_seq OWNED BY petitions.id;


--
-- Name: petitions_users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE petitions_users (
                                        petition_id bigint NOT NULL,
                                        user_id bigint NOT NULL
);


ALTER TABLE petitions_users OWNER TO postgres;

--
-- Name: roles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE roles (
                              id bigint NOT NULL,
                              description character varying(255),
                              name character varying(255) NOT NULL
);


ALTER TABLE roles OWNER TO postgres;

--
-- Name: roles_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE roles_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE roles_id_seq OWNER TO postgres;

--
-- Name: roles_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE roles_id_seq OWNED BY roles.id;


--
-- Name: roles_permissions; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE roles_permissions (
                                          role_id bigint NOT NULL,
                                          permission_id character varying(255) NOT NULL
);


ALTER TABLE roles_permissions OWNER TO postgres;

--
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE users (
                              id bigint NOT NULL,
                              created_at timestamp without time zone,
                              first_name character varying(255),
                              inn bigint NOT NULL,
                              last_name character varying(255),
                              password character varying(255) NOT NULL,
                              patr_name character varying(255),
                              username character varying(255) NOT NULL,
                              file_id bigint,
                              role_id bigint NOT NULL
);


ALTER TABLE users OWNER TO postgres;

--
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE users_id_seq OWNER TO postgres;

--
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE users_id_seq OWNED BY users.id;


--
-- Name: categories id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY categories ALTER COLUMN id SET DEFAULT nextval('categories_id_seq'::regclass);


--
-- Name: comments id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY comments ALTER COLUMN id SET DEFAULT nextval('comments_id_seq'::regclass);


--
-- Name: files id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY files ALTER COLUMN id SET DEFAULT nextval('files_id_seq'::regclass);


--
-- Name: petitions id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY petitions ALTER COLUMN id SET DEFAULT nextval('petitions_id_seq'::regclass);


--
-- Name: roles id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY roles ALTER COLUMN id SET DEFAULT nextval('roles_id_seq'::regclass);


--
-- Name: users id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY users ALTER COLUMN id SET DEFAULT nextval('users_id_seq'::regclass);


--
-- Name: categories categories_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY categories
    ADD CONSTRAINT categories_pkey PRIMARY KEY (id);


--
-- Name: comments comments_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY comments
    ADD CONSTRAINT comments_pkey PRIMARY KEY (id);


--
-- Name: files files_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY files
    ADD CONSTRAINT files_pkey PRIMARY KEY (id);


--
-- Name: permissions permissions_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY permissions
    ADD CONSTRAINT permissions_pkey PRIMARY KEY (name);


--
-- Name: petitions petitions_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY petitions
    ADD CONSTRAINT petitions_pkey PRIMARY KEY (id);


--
-- Name: petitions_users petitions_users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY petitions_users
    ADD CONSTRAINT petitions_users_pkey PRIMARY KEY (petition_id, user_id);


--
-- Name: roles_permissions roles_permissions_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY roles_permissions
    ADD CONSTRAINT roles_permissions_pkey PRIMARY KEY (role_id, permission_id);


--
-- Name: roles roles_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (id);


--
-- Name: users uk_kh5714iig58q66nj1o5ex4fii; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY users
    ADD CONSTRAINT uk_kh5714iig58q66nj1o5ex4fii UNIQUE (inn);


--
-- Name: users uk_r43af9ap4edm43mmtq01oddj6; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY users
    ADD CONSTRAINT uk_r43af9ap4edm43mmtq01oddj6 UNIQUE (username);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- Name: petitions fk15fr22sxbudshvsse1m53kvse; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY petitions
    ADD CONSTRAINT fk15fr22sxbudshvsse1m53kvse FOREIGN KEY (photo_id) REFERENCES files(id);


--
-- Name: petitions_users fk5y8l69d1mxyyq1iyfxi386r8a; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY petitions_users
    ADD CONSTRAINT fk5y8l69d1mxyyq1iyfxi386r8a FOREIGN KEY (user_id) REFERENCES users(id);


--
-- Name: petitions_users fk6q48q305fhgr0n11idqkvi7ad; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY petitions_users
    ADD CONSTRAINT fk6q48q305fhgr0n11idqkvi7ad FOREIGN KEY (petition_id) REFERENCES petitions(id);


--
-- Name: petitions fk8iqeno47tn02325mmlm8hf25v; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY petitions
    ADD CONSTRAINT fk8iqeno47tn02325mmlm8hf25v FOREIGN KEY (author_id) REFERENCES users(id);


--
-- Name: petitions fkbveuoyj33tdg5ssi75yw23pau; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY petitions
    ADD CONSTRAINT fkbveuoyj33tdg5ssi75yw23pau FOREIGN KEY (category_id) REFERENCES categories(id);


--
-- Name: roles_permissions fkbx9r9uw77p58gsq4mus0mec0o; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY roles_permissions
    ADD CONSTRAINT fkbx9r9uw77p58gsq4mus0mec0o FOREIGN KEY (permission_id) REFERENCES permissions(name);


--
-- Name: users fkdwnavs13341vej6x39qdoiqmt; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY users
    ADD CONSTRAINT fkdwnavs13341vej6x39qdoiqmt FOREIGN KEY (file_id) REFERENCES files(id);


--
-- Name: comments fkn2na60ukhs76ibtpt9burkm27; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY comments
    ADD CONSTRAINT fkn2na60ukhs76ibtpt9burkm27 FOREIGN KEY (author_id) REFERENCES users(id);


--
-- Name: comments fknai02u78dcm8y0a6hntsesntd; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY comments
    ADD CONSTRAINT fknai02u78dcm8y0a6hntsesntd FOREIGN KEY (petitions_id) REFERENCES petitions(id);


--
-- Name: users fkp56c1712k691lhsyewcssf40f; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY users
    ADD CONSTRAINT fkp56c1712k691lhsyewcssf40f FOREIGN KEY (role_id) REFERENCES roles(id);


--
-- Name: roles_permissions fkqi9odri6c1o81vjox54eedwyh; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY roles_permissions
    ADD CONSTRAINT fkqi9odri6c1o81vjox54eedwyh FOREIGN KEY (role_id) REFERENCES roles(id);


--
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE USAGE ON SCHEMA public FROM PUBLIC;


--
-- PostgreSQL database dump complete
--

