# language: nl
# encoding: utf-8

# User story #130
Functionaliteit: Als gemeente wil ik gegevens met indicatie onjuist niet tonen.
  Geen enkele GBA-categorie waarin de 'indicatie onjuist' is gevuld wordt geleverd. Dit geldt voor alle groepen,  ook historische gegevens over partner, verblijfplaats, en verblijfstitel. 
  Alleen voor de categorie 08.58 Verblijfplaats wordt de indicatie onjuist opgenomen.


  Maar bv. ook een onjuist opgenomen categorie 06 Overlijden.

  # Functionaliteit voor het niet tonen van partners, ouders en kinderen met indicatie onjuist is uitgewerkt in partners_ouders_kinderen.feature.

  Scenario: de persoon heeft een indicatie onjuist bij de gegevensgroep Overlijden
      Gegeven: de te raadplegen persoon met burgerservicenummer 999999023 heeft een indicatie onjuist bij de gegevensgroep Overlijden
      Als de persoon wordt geraadpleegd met burgerservicenummer 999999023
      Dan wordt de gegevensgroep overlijden niet getoond.
      
# Functionaliteit voor het tonen van onjuist verblijfsadres (alleen bij historische adressen).

  Scenario: de persoon heeft een indicatie onjuist bij de gegevensgroep Verblijfsplaats
      Gegeven: de te raadplegen persoon met burgerservicenummer 999999023 heeft een indicatie onjuist bij de gegevensgroep Verblijfsplaats
      Als de persoon wordt geraadpleegd met burgerservicenummer 999999023
      Dan wordt de gegevensgroep verblijfsplaats inclusief indicatie onjuist getoond.
