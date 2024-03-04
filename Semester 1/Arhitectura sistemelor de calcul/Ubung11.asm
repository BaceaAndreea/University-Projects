bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    ; ...
    ;MIT vorzeichen
    ;(8-a*b*100+c)/d+x
    ;a,b,d=byte
    ;c-doubleword
    ;x=quadword
    a db 7
    b db 8
    c dd 200
    d db 100
    x dq 30
    
    
; our code starts here
segment code use32 class=code
    start:
        ;(8-a*b*100+c)/d+x
        mov al,[a] ;wir tuen a in al...auf 8 bits
        mov bl,[b] ;wir tuen b in bl...auf 8 bits
        imul bl ;ax=a*b sind auf ax, 16 Bits...byte*byte=word                                   ax=7*8=56
        imul ax,100 ;man multipliziert mit ax=(a*b)*100                                         ax=56*100=5600
        
        mov bx,ax ;wir tuen ax in bx                                                            bx=5600
        mov ax,8
        sub ax,bx ;man subtrahiert von ax den bx...ax=8-a*b*100                                 ax=8-5600=-5592
        
        cwd ;wandelt das Wort AX in das Doppeltwort DX:AX um                                    dx:ax=-5592
        mov bx, word[c] 
        mov cx, word[c+2] ;man legt den c in cx:bx an                                           cx:bx=c
        add ax, bx ;man addiert in ax den bx
        adc dx, cx ; cx und der Inhalt des Carryflags wird zu dx addiert                        dx:ax=-5592+200=-5392
        ;das Ergebnis ist dx:ax=8-a*b*100+c
        
        mov cl,[d] ;cl bekommt d an
        mov ch,0 ;ch bekommt 0,um um vorzeichenlose Umwandlung von cl nach cx zu machen         cx=d
        idiv cx ; man teilt dx:ax/cx=(8-a*b*100+c)/d                                            ax=-5392/100=-53
        ;ax=(8-a*b*100+c)/d
     
        cwde ;wandelt das Wort AX in das Doppeltwort EAX um                                     eax=-53
        ;eax=a*b*100+c/d
        cdq ;wandelt das Doppeltwort EAX in das Quadwort EDX:EAX um                             edx:eax=(8-a*b*100+c)/d=-53
        
        add eax,dword[x]                                            
        adc edx, dword[x+4];edx:eax=(8-a*b*100+c)/d+x                                           edx:eax=-53+30=-23
       
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
