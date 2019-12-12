INSERT INTO Persoon (
    uuid,
    bsn,
    voornamen,
    geslachtsnaam,
    aanduidingAanschrijving,
    geboorteDatum,
    geboortePlaats,
    geboorteLand,
    geslachtsaanduiding,
    datumEersteInschrijvingGBA,
    indicatieGeheim,
    voorvoegsel,
    overlijdenDatum,
    overlijdenPlaats,
    overlijdenLand,
    adellijketitelpredikaat,
    datumOpschortingBijhouding,
    redenOpschortingBijhouding,
    indicatieGezagMinderjarige
    )
SELECT
    RANDOM_UUID(),
    burgerservicenummer,
    naam__voornamen,
    naam__geslachtsnaam,
    naam__aanduidingaanschrijving,
    geboorte__datum,
    geboorte__plaats,
    geboorte__land,
    geslachtsaanduiding,
    datuminschrijvingingemeente,
    CASE
        WHEN indicatiegeheim IS NULL THEN null
        WHEN indicatiegeheim  = '1' THEN true
        WHEN indicatiegeheim  = '0' THEN false
        ELSE null
    END,
    naam__voorvoegsel,
    overlijden__datum,
    overlijden__plaats,
    overlijden__land,
    naam__adellijketitel_predikaat,
    datumOpschortingBijhouding,
    redenOpschortingBijhouding,
    indicatiegezagminderjarige
FROM testdata1
WHERE
    burgerservicenummer IS NOT NULL ;

--  insert alle verblijfsplaatsen, huidige EN historie, van TESTDATA1
INSERT INTO Verblijfplaats (
  persoon_id,
  functieAdres,
  gemeenteVanInschrijving,
  postcode,
  huisnummer,
  naamopenbareruimte,
  huisletter,
  huisnummerToevoeging,
  identificatiecodeNummeraanduiding,
  ingangsdatumGeldigheid,
  datumVestigingInNederland
  )
SELECT
  (select p.id from Persoon p where p.bsn = t.burgerservicenummer),
  t.verblijfplaats__adresherkomst,
  t.verblijfplaats__gemeentevaninschrijving,
  t.verblijfplaats__postcode,
  t.verblijfplaats__huisnummer,
  t.verblijfplaats__naamopenbareruimte,
  t.verblijfplaats__huisletter,
  t.verblijfplaats__huisnummertoevoeging,
  t.verblijfplaats__identificatiecodenummeraanduiding,
  t.VERBLIJFPLAATS__INGANGSDATUMGELDIGHEID,
  t.DATUMVESTIGINGINNEDERLAND
FROM TESTDATA1 t;

UPDATE persoon p SET p.VERBLIJFPLAATSHUIDIG_ID =
(SELECT v.id FROM verblijfplaats v where v.persoon_id = p.id)




